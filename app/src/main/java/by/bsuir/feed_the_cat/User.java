package by.bsuir.feed_the_cat;

public class User {
    String name;
    int highscore;

    public User() {}

    public User(String name) {
        this.name = name;
        this.highscore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
