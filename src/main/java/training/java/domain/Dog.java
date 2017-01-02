package training.java.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;
import java.util.UUID;

import static io.qala.datagen.RandomDate.beforeNow;
import static io.qala.datagen.RandomShortApi.*;

public class Dog {
    private String id = UUID.randomUUID().toString();
    private String name;
    /**
     * To pass the object over network we usually use ISO8601 format. That's where Offset based time is ideal since
     * ISO8601 relies on the offsets rather than zone ids. Hence the {@code XXXX} that uses the offsets.
     * <p>
     * It's important to use {@code uuuu} and not {@code yyyy} for years as
     * <a href="http://qala.io/blog/your-api-does-not-support-bc-time.html">yyyy supports only positive values</a>.
     * </p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSXXXX")
    private OffsetDateTime timeOfBirth;
    private double weight;
    private double height;

    public static Dog random() {
        Dog dog = new Dog();
        dog.name = alphanumeric(1, 100);
        dog.timeOfBirth = nullOr(beforeNow().offsetDateTime());
        dog.weight = positiveDouble();
        dog.height = positiveInteger();
        return dog;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(OffsetDateTime timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override public String toString() {
        return "Dog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        return id != null ? id.equals(dog.id) : dog.id == null;

    }

    @Override public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
