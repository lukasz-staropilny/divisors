package org.rockwell.divisors.service;

import org.rockwell.divisors.interfaces.IDivisorsResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple algorithm finding divisors of numbers.
 * Complexity for single number 'i' is O(i) = i*(1+sqrt(i))/2
 * Complexity for n sequential numbers is O(n) = n*(1+n*(1+sqrt(n)))/2 = n^2*sqrt(n)
 */
public class SimpleDivisorsResolver extends AbstractDivisorsResolver implements IDivisorsResolver {

    /**
     * For given input numbers maps those numbers to lists of their divisors.
     * @param input - list of numbers to process
     * @return - map of numbers and corresponding divisors.
     * @throws IllegalArgumentException
     */
    @Override
    public Map<Integer, List<Integer>> findDivisors(List<Integer> input) throws IllegalArgumentException {

        validateInput(input);
        Map<Integer, List<Integer>> result = new HashMap<>(input.size());
        input.stream().forEach(i -> result.put(i, findNumberDividers(i)));
        return result;
    }

    /**
     * Finds all dividers for given number.
     * @param number - number to find dividers.
     * @return list with dividers of given number.
     */
    private List<Integer> findNumberDividers(Integer number) {
        List<Integer> dividers = new ArrayList<>(number);
        for(int i=1; i<=Math.sqrt(number); i++) {
            if(number%i==0) {
                dividers.add(i);
                int j = number/i;
                if(j!=i)
                    dividers.add(number/i);
            }
        }
        return dividers;
    }
}
