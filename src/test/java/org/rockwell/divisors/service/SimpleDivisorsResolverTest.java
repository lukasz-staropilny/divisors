package org.rockwell.divisors.service;

import org.junit.Test;
import org.rockwell.divisors.interfaces.IDivisorsResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rockwell.divisors.constants.Constants.MAX_INPUT_SIZE;

public class SimpleDivisorsResolverTest {

    private final IDivisorsResolver iDivisorsResolver = new SimpleDivisorsResolver();

    @Test(expected = IllegalArgumentException.class)
    public void whenInputTooLarge_thenShouldThrowIllegalArgumentException() {
        List<Integer> inputNumbers = tooManyNumbers();
        iDivisorsResolver.findDivisors(inputNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNumbersRepeated_thenShouldThrowIllegalArgumentException() {
        List<Integer> inputNumbers = repeatingNumbers();
        iDivisorsResolver.findDivisors(inputNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNumberTooLow_thenShouldThrowIllegalArgumentException() {
        List<Integer> inputNumbers = tooLowNumber();
        iDivisorsResolver.findDivisors(inputNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNumberTooHigh_thenShouldThrowIllegalArgumentException() {
        List<Integer> inputNumbers = tooHighNumber();
        iDivisorsResolver.findDivisors(inputNumbers);
    }

    @Test
    public void whenGivenNumber1_thenShouldResolveItsDivisors() {
        // given
        List<Integer> inputNumber1 = useCase1();

        // when
        List<Integer> dividersOf1 = iDivisorsResolver.findDivisors(inputNumber1).get(Integer.valueOf(1));

        // then
        assertThat(dividersOf1.contains(Integer.valueOf(1))).isTrue();
    }

    @Test
    public void whenGivenNumbers1234_thenShouldResolveItsDivisors() {
        // given
        List<Integer> inputNumbers1234 = useCase1_2_3_4();

        // when
        Map<Integer, List<Integer>> resultMap = iDivisorsResolver.findDivisors(inputNumbers1234);

        // then
        assertThat(resultMap.get(Integer.valueOf(1)).contains(Integer.valueOf(1))).isTrue();

        assertThat(resultMap.get(Integer.valueOf(2)).contains(Integer.valueOf(1))).isTrue();
        assertThat(resultMap.get(Integer.valueOf(2)).contains(Integer.valueOf(2))).isTrue();

        assertThat(resultMap.get(Integer.valueOf(3)).contains(Integer.valueOf(1))).isTrue();
        assertThat(resultMap.get(Integer.valueOf(3)).contains(Integer.valueOf(2))).isFalse();
        assertThat(resultMap.get(Integer.valueOf(3)).contains(Integer.valueOf(3))).isTrue();

        assertThat(resultMap.get(Integer.valueOf(4)).contains(Integer.valueOf(1))).isTrue();
        assertThat(resultMap.get(Integer.valueOf(4)).contains(Integer.valueOf(2))).isTrue();
        assertThat(resultMap.get(Integer.valueOf(4)).contains(Integer.valueOf(3))).isFalse();
        assertThat(resultMap.get(Integer.valueOf(4)).contains(Integer.valueOf(4))).isTrue();
    }

    @Test
    public void whenGivenNumber20_thenShouldResolveItsDivisors() {
        // given
        List<Integer> inputNumber20 = useCase20();

        // when
        List<Integer> dividersOf20 = iDivisorsResolver.findDivisors(inputNumber20).get(Integer.valueOf(20));

        // then
        assertThat(dividersOf20.contains(Integer.valueOf(1))).isTrue();
        assertThat(dividersOf20.contains(Integer.valueOf(2))).isTrue();
        assertThat(dividersOf20.contains(Integer.valueOf(3))).isFalse();
        assertThat(dividersOf20.contains(Integer.valueOf(4))).isTrue();
        assertThat(dividersOf20.contains(Integer.valueOf(5))).isTrue();
        assertThat(dividersOf20.contains(Integer.valueOf(6))).isFalse();
        assertThat(dividersOf20.contains(Integer.valueOf(7))).isFalse();
        assertThat(dividersOf20.contains(Integer.valueOf(8))).isFalse();
        assertThat(dividersOf20.contains(Integer.valueOf(9))).isFalse();
        assertThat(dividersOf20.contains(Integer.valueOf(10))).isTrue();
        assertThat(dividersOf20.contains(Integer.valueOf(11))).isFalse();
        assertThat(dividersOf20.contains(Integer.valueOf(20))).isTrue();
    }



    private List<Integer> useCase1() {
        List<Integer> result = new ArrayList<>(1);
        result.add(1);
        return result;
    }

    private List<Integer> useCase1_2_3_4() {
        List<Integer> result = new ArrayList<>(4);
        result.add(1);
        result.add(2);
        result.add(3);
        result.add(4);
        return result;
    }

    private List<Integer> useCase20() {
        List<Integer> result = new ArrayList<>(1);
        result.add(20);
        return result;
    }

    private List<Integer> tooLowNumber() {
        List<Integer> input = new ArrayList<>(1);
        input.add(0);
        return input;
    }

    private List<Integer> tooHighNumber() {
        List<Integer> input = new ArrayList<>(1);
        input.add(21);
        return input;
    }

    private List<Integer> tooManyNumbers() {
        List<Integer> input = new ArrayList<>(MAX_INPUT_SIZE +1);
        for(int i = 0; i<= MAX_INPUT_SIZE; i++) {
            input.add(i);
        }
        return input;
    }

    private List<Integer> repeatingNumbers() {
        List<Integer>  input = new ArrayList<>(MAX_INPUT_SIZE);
        for(int i = 0; i< MAX_INPUT_SIZE -1; i++) {
            input.add(i);
        }
        input.add(0);
        return input;
    }
}
