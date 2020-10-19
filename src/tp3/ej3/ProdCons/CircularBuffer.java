package tp3.ej3.ProdCons;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** Este código es distribuido como parte de un trabajo práctico de
 *  la materia Sistemas Operativos I dictada por la de Ciencias Exactas de
 *  la Universidad nacional del centro de la provincia de Buenos Aires (UNICEN).
 *  El código no debe usarse en ningún otro proyecto debido a que contiene o 
 *  puede contener malas prácticas y errores introducidos intencionalmente con 
 *  fines didácticos. Así mismo el código carece de cualquier tipo de optimización
 *  primando la legibilidad del mismo.
 *  @author Dr. Juan Manuel Rodriguez
*/

public class CircularBuffer<T> implements IBuffer<T> {

	private Object[] elements;
	private int posNext;
	private int lastElem;
	private Lock lock = new ReentrantLock();
	private Condition bufferFull = lock.newCondition();
	private Condition bufferEmpty = lock.newCondition();

	/**
	 * Crea un buffer de 10 elementos
	 */
	public CircularBuffer(){
		this(10);
	}
	/**
	 * Crea un buffer de size elementos
	 * @param size
	 */
	public CircularBuffer(int size){
		this.elements = new Object[size];
		this.lastElem = 0;
		this.posNext = 0;
	}
	@Override
	public T next() {
		this.lock.lock();
		try {
			while (this.posNext == this.lastElem) {
				System.out.println("El thread " + Thread.currentThread().getName() + " espera porque el buffer esta vacio");
				bufferEmpty.await(); //espero a que deje de estar vacio
			}
			@SuppressWarnings("unchecked")
			T e = (T) this.elements[this.posNext];
			this.posNext = (this.posNext + 1) % this.elements.length;
			this.bufferFull.signal(); //ya no esta lleno porque saque uno
			return e;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} finally {
			this.lock.unlock();
		}
	}

	@Override
	public void add(T data) {
		this.lock.lock();
		try {
			while (((this.posNext + 1) % this.elements.length) == this.lastElem) {
				System.out.println("El thread " + Thread.currentThread().getName() + " espera porque el buffer esta lleno");
				this.bufferFull.await(); //espero a que deje de estar lleno
			}
			int p = this.lastElem;
			this.lastElem = (this.lastElem + 1) % this.elements.length;
			this.elements[p] = data;
			this.bufferEmpty.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}

	@Override
	public int size() {
		return (this.lastElem-this.posNext)%this.elements.length;
	}

	@Override
	public int maxElements() {
		return this.elements.length;
	}

}
