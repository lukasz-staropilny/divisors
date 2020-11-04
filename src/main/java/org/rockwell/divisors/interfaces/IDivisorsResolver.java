package org.rockwell.divisors.interfaces;

import java.util.List;
import java.util.Map;

public interface IDivisorsResolver {
    Map<Integer, List<Integer>> findDivisors(List<Integer> input) throws IllegalArgumentException;
}
