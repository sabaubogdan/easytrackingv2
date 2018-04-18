package xyz.vegaone.easytrackingv2.util;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {

    private Mapper mapper;

    public MapperUtil(Mapper mapper) {
        this.mapper = mapper;
    }

    public <T, U> List<U> mapList(List<T> source, Class<U> destinationClass) {

        return source
                .stream()
                .map(item -> mapper.map(item, destinationClass))
                .collect(Collectors.toList());
    }
}
