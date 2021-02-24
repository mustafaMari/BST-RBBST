package Class7;

public interface IExecutor<T , R> {
	void execute (T elem); 
	R getResult();
}
