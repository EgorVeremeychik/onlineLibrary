package dao.pool.Exception;

/**
 * Created by EgorVeremeychik on 23.04.2016.
 */
public class ConnectionPoolException extends Exception
{
    public ConnectionPoolException(){}

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(String message,Throwable cause){
        super(message,cause);
    }

    public ConnectionPoolException(Throwable cause){
        super(cause);
    }
}
