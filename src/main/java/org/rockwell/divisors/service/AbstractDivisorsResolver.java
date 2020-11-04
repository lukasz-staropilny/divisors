package org.rockwell.divisors.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.rockwell.divisors.constants.Constants.*;

/**
 * Base abstract class for DivisorsResolvers.
 * Has method to validate input data.
 */
public abstract class AbstractDivisorsResolver {

    /**
     * Validates list of input numbers according to conditions:
     *  - number can't be too high
     *  - number can't be too low
     *  - list can't be too long
     *  - each number must be unique
     * @param input - list with numbers to validate.
     * @throws IllegalArgumentException if any of required conditions is not met.
     */
    protected void validateInput(List<Integer> input) throws IllegalArgumentException {
        if(input.size()> MAX_INPUT_SIZE) {
            throw new IllegalArgumentException("Too many input numbers");
        }
        Set<Integer> numbersSet = new HashSet<>(input);
        if(numbersSet.size()<input.size()) {
            throw new IllegalArgumentException("Repeating numbers in input");
        }
        for(Integer i:input) {
            if(i>MAX_INPUT_VALUE) {
                throw new IllegalArgumentException("Input number is too high");
            }
            if(i<MIN_INPUT_VALUE) {
                throw new IllegalArgumentException("Input number is too low");
            }
        }
    }
}
