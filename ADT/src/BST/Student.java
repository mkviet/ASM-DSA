package BST;

public class Student {
    String name;
    String studentCode;
    double score;
    String rank;

    public Student(String name, String studentCode, double score) {
        this.name = name;
        this.studentCode = studentCode;
        this.score = score;
        this.rank = assignRank(score);
    }

    String assignRank(double score) {
        if (score >= 0 && score <= 5.0) {
            return "Fail";
        } else if (score > 5.0 && score <= 6.5) {
            return "Medium";
        } else if (score > 6.5 && score <= 7.5) {
            return "Good";
        } else if (score > 7.5 && score <= 9.0) {
            return "Very Good";
        } else if (score > 9.0 && score <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid Score";
        }
    }

    public String getName() {
        return name;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public double getScore() {
        return score;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return name + ", Code: " + studentCode + ", Score: " + score + ", Rank: " + rank;
    }

    void setScore(double newScore) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void setName(String newName) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
