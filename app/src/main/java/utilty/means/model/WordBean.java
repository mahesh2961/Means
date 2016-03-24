package utilty.means.model;

import java.io.Serializable;

/**
 * Created by mahesh on 24/3/16.
 */
public class WordBean implements Serializable
{
    private static final long serialVersionUID = 406671816413754989L;
    int id;
    String word;
    int variant;
    String meaning;
    double ratio;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public int getVariant()
    {
        return variant;
    }

    public void setVariant(int variant)
    {
        this.variant = variant;
    }

    public String getMeaning()
    {
        return meaning;
    }

    public void setMeaning(String meaning)
    {
        this.meaning = meaning;
    }

    public double getRatio()
    {
        return ratio;
    }

    public void setRatio(double ratio)
    {
        this.ratio = ratio;
    }
}
