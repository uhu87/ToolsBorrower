package pl.uhu87.toolsborrower.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.uhu87.toolsborrower.entity.UserTool;
import pl.uhu87.toolsborrower.repository.UserToolRepository;

@Component
public class UserToolConverter implements Converter<String, UserTool> {

    private final UserToolRepository userToolRepository;


    public UserToolConverter(UserToolRepository userToolRepository) {
        this.userToolRepository = userToolRepository;
    }



    @Override
    public UserTool convert(String source) {
        return userToolRepository.getById(Long.parseLong(source));
    }
}
