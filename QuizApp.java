import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    int correctAnswerIndex;

    Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizApp {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static List<Question> questions = new ArrayList<>();
    private static boolean answered = false;
    private static Timer timer = new Timer();
    
    public static void main(String[] args) {
        // Initialize quiz questions
        initializeQuestions();

        // Start the quiz
        startQuiz();
    }

    private static void initializeQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, 0));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, 3));
        // Add more questions as needed...
    }

    private static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestionIndex < questions.size()) {
            answered = false;
            displayQuestion();

            // Start the timer for the current question
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answered) {
                        System.out.println("Time's up! Moving to the next question.");
                        currentQuestionIndex++;
                        startQuiz();
                    }
                }
            }, 30000); // 30 seconds timer

            int userAnswer = scanner.nextInt() - 1; // Subtract 1 for zero-based index
            answered = true;
            timer.cancel(); // Stop the timer
            timer = new Timer(); // Reset timer for the next question

            if (userAnswer == questions.get(currentQuestionIndex).correctAnswerIndex) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was " + (questions.get(currentQuestionIndex).correctAnswerIndex + 1));
            }

            currentQuestionIndex++;
        }

        displayResult();
    }

    private static void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.questionText);
        for (int i = 0; i < currentQuestion.options.length; i++) {
            System.out.println((i + 1) + ". " + currentQuestion.options[i]);
        }
        System.out.print("Your answer (1-" + currentQuestion.options.length + "): ");
    }

    private static void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.questionText);
            System.out.println("Correct answer: " + q.options[q.correctAnswerIndex]);
        }
    }
}
