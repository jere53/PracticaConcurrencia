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

public class OneElementBuffer<T> implements IBuffer<T> {

	private T element;
	private Lock lock = new ReentrantLock();
	private Condition bufferLleno = lock.newCondition();
	private Condition bufferVacio = lock.newCondition();

	@Override
	public T next() {
		lock.lock();
		try {
			while (this.element == null) {
				System.out.println("El thread " + Thread.currentThread().getName() + " espera porque el buffer esta vacio");
				bufferVacio.await();
			}
			T res = this.element;
			this.element = null;
			bufferLleno.signal();
			return res;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void add(T data) {
		lock.lock();
		try {
			while (this.element != null) {
				System.out.println("El thread " + Thread.currentThread().getName() + " espera porque el buffer esta lleno");
				bufferLleno.await();
			}
			this.element = data;
			bufferVacio.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public int size() {
		return this.element==null ? 0:1;
	}

	@Override
	public int maxElements() {
		return 1;
	}

}
