package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

enum Error{
    ERROR("[ERROR] 올바른 입력을 하세요");

    private final String error;

    Error(String error){
        this.error = error;
    }

    public String getError(){
        return error;
    }
}

class money_for_lotto{
    public static int realNumber;

    public static int realMoney(String inputNumber){
        moneyIsNumber(inputNumber);
        realNumber = Integer.parseInt(inputNumber);
        moneyIsOver0(realNumber);
        multipleOfThousand(realNumber);
        return Integer.parseInt(inputNumber);
    }

    public static void moneyIsNumber(String inputNumber){
        try{
            Integer.parseInt(inputNumber);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요");
        }
    }

    public static void moneyIsOver0(int realNumber){
        if(realNumber<1){
            throw new IllegalArgumentException("[ERROR] 0보다 큰 수를 입력하세요");
        }
    }
    public static void multipleOfThousand(int realNumber){
        if(realNumber%1000 != 0){
            throw new IllegalArgumentException("[ERROR] 1000의 배수를 입력하세요");
        }
    }

    public static int repeatTime(int realNumber){
        return realNumber/1000;
    }

    public static int exceptionRepeatMoney(){
        int result = 0;
        while (result == 0) {
            result = readLine(result);
        }
        return result;
    }

    public static int readLine(int result) {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            String inputMoney = Console.readLine();
            result = money_for_lotto.realMoney(inputMoney);
        } catch (IllegalArgumentException e) {
            System.out.println(Error.ERROR.getError());
        }
        return result;
    }
}

class random_lotto{
    static final List<List<Integer>> totalLotto = new ArrayList<>();

    public static List<List<Integer>> print_lotto(int repeatTime){
        save_lotto(repeatTime);
        for(int i = 0; i<repeatTime; i++){
            System.out.println(totalLotto.get(i));
        }
        return totalLotto;
    }

    public static void save_lotto(int repeatTime){
        for(int i = 0; i<repeatTime; i++){
            totalLotto.add(pick_lotto());
        }
    }
    public static List<Integer> pick_lotto(){
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}

class winning_number{

    public static List<Integer> splitInput(String input){
        String[] winningNumbers = input.split(",");
        return changeAllAsInt(winningNumbers);
    }

    public static List<Integer> changeAllAsInt(String[] numbers){
        List<Integer> splitedWinNumber = new ArrayList<>();
        for (String number : numbers) {
            checkIntOrNot(number);
            splitedWinNumber.add(changeAsInt(number));
        }
        Lotto lotto = new Lotto(splitedWinNumber);
        return splitedWinNumber;
    }

    public static void checkIntOrNot(String number){
        try{
            Integer.parseInt(number);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 숫자를 입력하세요");
        }
    }

    public static int changeAsInt(String strNumber){
        return Integer.parseInt(strNumber);
    }

    public static List<Integer> exceptionRepeatWinNumber(){
        List<Integer> repeatOrNot = new ArrayList<>();
        while(repeatOrNot.isEmpty()){
            repeatOrNot = readLine(repeatOrNot);
        }
        return repeatOrNot;
    }

    public static List<Integer> readLine(List<Integer> repeat){
        try{
            System.out.println("\n당첨 번호를 입력해 주세요.");
            String winningNumber = Console.readLine();
            repeat = winning_number.splitInput(winningNumber);
        }catch (IllegalArgumentException e) {
            System.out.println(Error.ERROR.getError());
        }
        return repeat;
    }
}

class bonus_number{
    public static List<Integer> addBonus(List<Integer> winNumbers, String bonus){
        int bonusNumber = intgerBonus(bonus);
        checkBonusRange(bonusNumber);
        circulationForCheck(winNumbers, bonusNumber);
        winNumbers.add(bonusNumber);
        return winNumbers;
    }

    public static int intgerBonus(String bonus){
        try {
            Integer.parseInt(bonus);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 숫자를 입력");
        }
        return Integer.parseInt(bonus);
    }

    public static void checkBonusRange(int number){
        if(number<1||number>45){
            throw new IllegalArgumentException("[ERROR] 범위가 다름");
        }
    }

    public static void circulationForCheck(List<Integer> criterias, int number){
        for(int criteria : criterias){
            checkDuplication(criteria, number);
        }
    }

    public static void checkDuplication(int criteria, int number){
        if(criteria == number){
            throw new IllegalArgumentException("[ERROR] 당첨 숫자 외 번호를 입력");
        }
    }
}

public class Application {
    public static void main(String[] args) {
        int realMoney = money_for_lotto.exceptionRepeatMoney();
        int repeatTime = money_for_lotto.repeatTime(realMoney);

        System.out.println("\n" + repeatTime + "개를 구매했습니다.");
        List<List<Integer>> totalLotto = random_lotto.print_lotto(repeatTime);

        List<Integer> winningNumber = winning_number.exceptionRepeatWinNumber();

        System.out.println("\n보너스 번호를 입력해 주세요.");
        String bonusNumber = Console.readLine();
        bonus_number.addBonus(winningNumber, bonusNumber);

    }
}
