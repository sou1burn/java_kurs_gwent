package Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Card implements Serializable {
    
    private int power;
    private String type;
    private String name;
    //private String description;
    //private String ability;
    @JsonIgnore
    private transient ImageIcon cardImage;
    private String path;

    public Card(int power, String type, String name, String path)
    {
        this.power = power;
        this.type = type;
        this.name = name;
        this.path = path;
        try {
            setCardImg(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public String getName()
    {
        return this.name;
    }

    public String getType()
    {
        return this.type;
    }

    public int getPower()
    {
        return this.power;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCardImg(String path) throws IOException
    {
        this.cardImage = new ImageIcon(ImageIO.read(new File(path)));
    }

    public String getPath()
    {
        return this.path;
    }

    @Override 
    public String toString()
    {
        return String.format("Card{name='%s', power=%d, type='%s', description = '%s''}", name, power, type);
    }

    public int getRowIndex()
    {
        if (this.type == "melee")
        {
            return 0;
        }
        else if (this.type == "mid")
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
    @JsonIgnore
    public ImageIcon getImage()
    {
        return cardImage;
    }

    public void setImage(ImageIcon icon)
    {
        this.cardImage = icon;
    }

}
