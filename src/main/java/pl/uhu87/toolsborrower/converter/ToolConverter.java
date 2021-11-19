package pl.uhu87.toolsborrower.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.repository.ToolRepository;


@Component
public class ToolConverter implements Converter<String, Tool> {

    private final ToolRepository toolRepository;

    public ToolConverter(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }


    @Override
    public Tool convert(String source) {
        return toolRepository.getById(Long.parseLong(source));
    }
}
