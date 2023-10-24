package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static baseball.BaseballConstants.*;

public class Computer {
    /**
     * 컴퓨터의 수를 생성
     * @return 1~9까지의 숫자 3개가 담긴 리스트
     */
    public static List<Integer> getRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < NUMBER_LENGTH){
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if(!numbers.contains(randomNumber)){
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }
}
