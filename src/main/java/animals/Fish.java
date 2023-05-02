package animals;

import food.Food;
import food.Grass;
import food.WrongFoodException;
import model.Size;

public class Fish extends Herbivore implements Swim{

    public Fish(String name) {
        super(name);
    }

    public Fish(String name, int satiety) {
        super(name);
        this.satiety = satiety;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    @Override
    public String swim() {
        System.out.println("рыбка плавает");
        return "рыбка плавает";
    }

    @Override
    public void eat(Food food) {
        try {
            if (!(food instanceof Grass)) {
                throw new WrongFoodException("ошибка");
            } else {
                this.satiety += food.getEnergy();
            }
        } catch (WrongFoodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Size getSize() {
        return Size.MEDIUM;
    }


}
