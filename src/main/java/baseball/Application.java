package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        // 프로그램 시작
        System.out.println("숫자 야구 게임을 시작합니다.");

        while(true) {
            List<Integer> computer = getRandomNumbers();

            // 사용자가 번호 맞추기 시작
            while(true) {
                System.out.print("숫자를 입력해주세요 : ");
                List<Integer> user = getUserNumbers();

                int strike = getStrike(computer, user);
                int ball = getBall(computer, user);

                if (strike == 3) {
                    System.out.println("3스트라이크");
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    break;
                } else if (strike > 0 && ball > 0) {
                    System.out.println(ball + "볼 " + strike + "스트라이크");
                } else if (strike > 0 && ball == 0) {
                    System.out.println(strike + "스트라이크");
                } else if (strike == 0 && ball > 0){
                    System.out.println(ball + "볼");
                } else if (strike == 0 && ball == 0) {
                    System.out.println("낫싱");
                }
            }

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String restart = Console.readLine();

            if (restart.equals("2")){
                Console.close();
                break;
            }

            if (!restart.equals("1")){
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * 컴퓨터의 수를 생성
     * @return 1~9까지의 숫자 3개가 담긴 리스트
     */
    public static List<Integer> getRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 3){
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if(!numbers.contains(randomNumber)){
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    /**
     * 사용자가 입력한 수를 예외처리와 동시에 List형태로 변환
     * @return 1~9까지의 숫자 3개가 담긴 리스트
     */
    public static List<Integer> getUserNumbers() {
        List<Integer> numbers = new ArrayList<>();

        String input = Console.readLine();
        if (input.length() > 3){
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (Character.isDigit(c) && 49 <= (int) c && 57 >= (int) c && !numbers.contains(Character.getNumericValue(c))){
                numbers.add(Character.getNumericValue(c));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return numbers;
    }

    /**
     * 위치별 번호가 같은 값의 갯수를 구한다.
     * @param computer 컴퓨터의 번호
     * @param user 사용자가 입력한 번호
     * @return 컴퓨터와 사용자의 번호 간의 스트라이크 갯수
     */
    public static int getStrike(List<Integer> computer, List<Integer> user) {
        int res = 0;

        for (int i=0; i < 3; i++){
            if (computer.get(i).equals(user.get(i))){
                res++;
            }
        }
        return res;
    }

    /**
     * 위치별로 번호는 다르지만 사용자가 입력한 번호가 컴퓨터의 번호 안에 존재하는 값의 갯수를 구한다.
     * @param computer 컴퓨터의 번호
     * @param user 사용자가 입력한 번호
     * @return 컴퓨터와 사용자의 번호 간의 볼 갯수
     */
    public static int getBall(List<Integer> computer, List<Integer> user) {
        int res = 0;

        for (int i=0; i < 3; i++){
            if (!computer.get(i).equals(user.get(i)) && computer.contains(user.get(i))){
                res++;
            }
        }
        return res;
    }
}
