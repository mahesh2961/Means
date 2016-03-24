package utilty.means.utils;

/**
 * @author mahesh.baraskar
 *
 */
public class Result
{

	boolean isSuccess = false;

	String message = null;

	Object data = null;

	public boolean getIsSuccess()
	{

		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess)
	{

		this.isSuccess = isSuccess;
	}

	public String getMessage()
	{

		return message;
	}

	public void setMessage(String message)
	{

		this.message = message;
	}

	public Object getData()
	{

		return data;
	}


	public void setData(Object data)
	{

		this.data = data;
	}


}
