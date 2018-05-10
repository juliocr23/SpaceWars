package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//png for sprites
public class Animation {

    Image[] image;
    int current = 0;
    int counter = 0;
    int delay;
    int duration;
    int max;

    public Animation(){
        current = 0;
        delay = 0;
        duration = 0;
        counter = -1;
        max = 0;
    }

    public Animation(int size){
        max = size;
        image  = new Image[size];

        current = 0;
        delay = 0;
        duration = 0;
        counter = -1;
    }

    public void update(){
      if(isAnimationOver())
          reset();
    }


    public Animation(String file, String format,int length, int duration) {

        this.duration = duration;
        delay = duration;
        image = new Image[length];

        for (int i = 1; i < length; i++) {
            String fileName = file + i + format;
            //System.out.println(fileName);
            try {
                image[i] = ImageIO.read(new File(fileName));
            } catch (IOException e) {
            	//e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Image file not found");
            }
        }
    }

    public void addImg(Image img){
        if(counter<max) {
            image[counter] = img;
            counter++;
        }
    }

    public void setAnimationSize(int length){
        max = length;
        image = new Image[length];
    }

    public void setTime(int length){
        duration = length;
        delay = length;
    }


    public void setDuration(int d){
        duration += d;
        delay    += d;
    }

    public Image staticImage() {
        return image[0];
    }

    public Image nextImage() {

        if(current != image.length-1) {
            if (delay == 0) {
                current++;
            /*if (current == image.length - 1) {
                current = 0;
            }*/
                delay = duration;
            } else
                delay--;
        }

        return image[current];
    }

    public void reset(){
        current = 0;
    }

    public Image previousImage() {

        if (current > 0){
            if (delay == 0) {
                current--;
                if (current == 0) {
                    current = image.length - 1;
                }
                delay = duration;
            } else
                delay--;
            System.out.println(current);
        }

        return image[current];
    }


    public void setCurrent(int i){
        current = i;
    }

    public Image getCurrentImg(){
        return image[current];
    }

    public boolean isAnimationOver(){
        return current == image.length-1;
    }

    public int getCurrentIndex(){
        return current;
    }

    public int getLength(){
        return image.length;
    }

}
