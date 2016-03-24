package utilty.means.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 24/3/16.
 */
public class HttpsWordResponse implements Serializable
{
    private static final long serialVersionUID = 406671816413757989L;

    int version;

    List<WordBean> words;

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public List<WordBean> getWords()
    {
        return words;
    }

    public void setWords(List<WordBean> words)
    {
        this.words = words;
    }
}
