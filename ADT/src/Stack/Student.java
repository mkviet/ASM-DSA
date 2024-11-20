package Stack;

public class Student {
    private String name;
    private String studentCode;
    private double score;

    public Student(String name, String studentCode, double score) {
        this.name = name;
        this.studentCode = studentCode;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRank() {
        if (score >= 9.0) {
            return "Excellent";
        } else if (score >= 7.5) {
            return "Very Good";
        } else if (score >= 6.5) {
            return "Good";
        } else if (score >= 5.0) {
            return "Medium";
        } else {
            return "Fail";
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Code: " + studentCode + ", Score: " + score;
    }
}
