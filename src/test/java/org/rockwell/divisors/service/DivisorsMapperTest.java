package org.rockwell.divisors.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rockwell.divisors.constants.Constants.MAX_SETUP_SIZE;

public class DivisorsMapperTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenTooManyMappingSetups_thenShouldThrowIllegalArgumentException() {
        // given
        Map<String, Map<Integer, String>> configuration = tooLargeConfiguration();

        // when
        DivisorsMapper divisorsMapper = new DivisorsMapper(configuration);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMappingHasRepeatedValues_thenShouldThrowIllegalArgumentException() {

        // given
        Map<String, Map<Integer, String>> configuration = repeatedValuesInMapping();

        // when
        DivisorsMapper divisorsMapper = new DivisorsMapper(configuration);
    }

    @Test
    public void withExampleConfiguration_shouldReturnCorrectValues() {
        // given
        Map<String, Map<Integer, String>> configuration = exampleConfiguration();
        List<Integer> integers = new ArrayList<>(1);
        integers.add(Integer.valueOf(2));

        // when
        DivisorsMapper divisorsMapper = new DivisorsMapper(configuration);

        // then
        assertThat(divisorsMapper.getMappedDivisors("Animals", integers).contains("Cat")).isTrue();
        assertThat(divisorsMapper.getMappedDivisors("Furnitures", integers).contains("Table")).isTrue();
    }





    private Map<String, Map<Integer, String>> tooLargeConfiguration() {
        Map<String, Map<Integer, String>> configuration = new HashMap<>(MAX_SETUP_SIZE+1);
        configuration.put("a", new HashMap<>());
        configuration.put("b", new HashMap<>());
        configuration.put("c", new HashMap<>());
        configuration.put("d", new HashMap<>());
        configuration.put("e", new HashMap<>());
        configuration.put("f", new HashMap<>());
        configuration.put("g", new HashMap<>());
        configuration.put("h", new HashMap<>());
        configuration.put("i", new HashMap<>());
        configuration.put("j", new HashMap<>());
        configuration.put("k", new HashMap<>());
        return configuration;
    }

    private Map<String, Map<Integer, String>> repeatedValuesInMapping() {
        Map<Integer, String> animalsMap = new HashMap<>();
        animalsMap.put(Integer.valueOf(1), "Mouse");
        animalsMap.put(Integer.valueOf(2), "Mouse");
        Map<String, Map<Integer, String>> configuration = new HashMap<>(1);
        configuration.put("Animals", animalsMap);
        return configuration;
    }

    private Map<String, Map<Integer, String>> exampleConfiguration() {

        Map<Integer, String> animalsMap = new HashMap<>();
        animalsMap.put(Integer.valueOf(1), "Mouse");
        animalsMap.put(Integer.valueOf(2), "Cat");
        animalsMap.put(Integer.valueOf(3), "Dog");
        animalsMap.put(Integer.valueOf(4), "Elephant");

        Map<Integer, String> furnituresMap = new HashMap<>();
        furnituresMap.put(Integer.valueOf(1), "Chair");
        furnituresMap.put(Integer.valueOf(2), "Table");
        furnituresMap.put(Integer.valueOf(3), "Cabinet");
        furnituresMap.put(Integer.valueOf(4), "Bed");

        Map<String, Map<Integer, String>> configuration = new HashMap<>(2);
        configuration.put("Animals", animalsMap);
        configuration.put("Furnitures", furnituresMap);
        return configuration;
    }

    private List<Integer> exampleIntegers() {
        List<Integer> integerList = new ArrayList<>(4);
        integerList.add(Integer.valueOf(1));
        integerList.add(Integer.valueOf(2));
        integerList.add(Integer.valueOf(3));
        integerList.add(Integer.valueOf(4));
        return integerList;
    }
}
