package org.rockwell.divisors.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.rockwell.divisors.constants.Constants.MAX_SETUP_SIZE;

/**
 * Maps divisors to names according to configured mapping.
 * Accepts up to 10 different setups of mappings.
 */
public class DivisorsMapper {
    private Map<String, Map<Integer, String>> mappingSetups;

    /**
     * Default constructor without configuration settings
     */
    public DivisorsMapper() {
    }

    /**
     * Constructor accepts mapping setups
     * @param mappingSetups - mapping setup where configuration with given name is mapped to numbers mapping map
     */
    public DivisorsMapper(Map<String, Map<Integer, String>> mappingSetups) {
        validateMappingSetups(mappingSetups);
        this.mappingSetups = mappingSetups;
    }

    /**
     * Sets new configuration
     * @param mappingSetups - mapping setup where configuration with given name is mapped to numbers mapping map
     */
    public void setMappingSetups(Map<String, Map<Integer, String>> mappingSetups) {
        validateMappingSetups(mappingSetups);
        this.mappingSetups = mappingSetups;
    }

    /**
     * Validates input configuration according to conditions:
     *  - maximum 10 setups of mapping
     *  - values in each mapping must be unique
     * @param mappingSetups - mapping setup where configuration with given name is mapped to numbers mapping map
     * @throws IllegalArgumentException - if any of described conditions is not met
     */
    private void validateMappingSetups(Map<String, Map<Integer, String>> mappingSetups) throws IllegalArgumentException {
        if(mappingSetups.size()>MAX_SETUP_SIZE) {
            throw new IllegalArgumentException("Too many mapping setups");
        }
        for(Map<Integer, String> numbersMap:mappingSetups.values()) {
            validateValues(numbersMap);
        }
    }

    /**
     * Validates values in mappings. Values must be unique.
     * @param numbersMap - number to value map
     */
    private void validateValues(Map<Integer, String> numbersMap) throws IllegalArgumentException {
        Set<String> valuesSet = new HashSet<>(numbersMap.values());
        if(valuesSet.size()!= numbersMap.size()) {
            throw new IllegalArgumentException("Values in mapping must be unique");
        }
    }

    /**
     * Return mapped divisors according to chosen mapping configuration
     * @param mappingName - name of used mapping configuration
     * @param divisors - numbers to map
     * @return
     * @throws IllegalArgumentException - if mapping setups are not set or mapping with given name not exist
     */
    public List<String> getMappedDivisors(String mappingName, List<Integer> divisors) throws IllegalArgumentException {
        if(mappingSetups==null) {
            throw new IllegalArgumentException("Mapping setups not configured");
        }
        Map<Integer, String> numbersMapping = mappingSetups.get(mappingName);
        if(numbersMapping==null) {
            throw new IllegalArgumentException("Mapping with name "+mappingName+" does not exist");
        }
        return divisors.stream()
                .map(integer -> numbersMapping.get(integer))
                .filter(s -> s!=null)
                .collect(Collectors.toList());
    }
}
