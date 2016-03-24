package utilty.means.utils;

/**
 * Created by mahesh on 24/3/16.
 */
public class CustomException extends Exception
{
    String message;

    int type;

    public CustomException(String message,int type)
    {
        super(message);
        this.message = message;
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
