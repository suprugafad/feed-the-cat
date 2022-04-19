package by.bsuir.feed_the_cat;

public class User {
    String name;
    int highScore;

    public User() {}

    public User(String name) {
        this.name = name;
        this.highScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
