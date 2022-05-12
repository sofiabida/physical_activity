package KW_2;

public class Sport {
    String name;
    double hours;
    double minutes;
    double calories;

    public Sport(String name, double hours, double minutes) {
        this.name = name;
        this.hours = hours;
        this.minutes = minutes;

        switch (name) {
            case "Біг":
                this.calories = 2;
                break;
            case "Хотьба":
                this.calories = 1;
                break;
            case "Плавання":
                this.calories = 3;
                break;
            case "Їзда на велосипеді":
                this.calories = 4;
                break;
        }
    }

    public double CalculateCalories() {
        double totalCalories;
        totalCalories = hours * calories *60 + minutes * calories;
        return totalCalories;
    }


    public String CalculateActivity() {
        double burnt = this.CalculateCalories();
        if (burnt < 200) {
            return "Низький";
        } else if (burnt > 600) {
            return "Високий";
        } else {
            return "Середній";
        }
    }
}

