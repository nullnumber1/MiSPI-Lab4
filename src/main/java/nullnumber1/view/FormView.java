package nullnumber1.view;

import lombok.Data;
import nullnumber1.dto.PointDTO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("formView")
@SessionScoped
@Data
public class FormView implements Serializable {

    private final int[] valueX = {-3, -2, -1, 0, 1, 2, 3};
    private final boolean[] booleanX = new boolean[7];

    private String strX;
    private String strR;

    private double inputY;

    // svg
    private double svgX;
    private double svgY;

    // table dto list
    private List<PointDTO> dtoList;

    @PostConstruct
    private void init() {
        dtoList = new ArrayList<>();
    }

    public List<PointDTO> getUserDTOList() {
        List<PointDTO> lst = new ArrayList<>();
        PointDTO pointDTO = new PointDTO();
        pointDTO.setX(Double.parseDouble(strX));
        pointDTO.setY(inputY);
        pointDTO.setR(Double.parseDouble(strR));
        lst.add(pointDTO);
        return lst;
    }

    public PointDTO getUserDTOSvg() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setX(svgX);
        pointDTO.setY(svgY);
        pointDTO.setR(Double.parseDouble(strR));
        return pointDTO;
    }
}
