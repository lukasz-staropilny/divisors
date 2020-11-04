package org.rockwell.divisors.controller;

import org.rockwell.divisors.interfaces.IDivisorsResolver;
import org.rockwell.divisors.service.DivisorsMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("divisors")
public class DivisorsController {

    private DivisorsMapper divisorsMapper;
    private IDivisorsResolver iDivisorsResolver;

    public DivisorsController(DivisorsMapper divisorsMapper, IDivisorsResolver iDivisorsResolver) {
        this.divisorsMapper = divisorsMapper;
        this.iDivisorsResolver = iDivisorsResolver;
    }


    @PutMapping(value = "configuration")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setMappingSetups(@RequestBody Map<String, Map<Integer, String>> configuration) {
        divisorsMapper.setMappingSetups(configuration);
    }

    @GetMapping(value = "mapping")
    public ResponseEntity<Map<Integer, List<String>>> getMappedDivisors(@RequestParam(name = "setup") String setup,
                                            @RequestParam(name = "numbers") List<Integer> numbers) {

        Map<Integer, List<Integer>> divisorsMap = iDivisorsResolver.findDivisors(numbers);
        Map<Integer, List<String>> result = new HashMap<>(divisorsMap.size());
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = divisorsMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            result.put(entry.getKey(), divisorsMapper.getMappedDivisors(setup, entry.getValue()));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
