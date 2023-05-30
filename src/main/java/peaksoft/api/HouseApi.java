package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.House;
import peaksoft.enums.HouseType;
import peaksoft.exceptions.MyException;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/houses/{agencyId}")
@RequiredArgsConstructor
public class HouseApi {
    private final HouseService houseService;

    @GetMapping
    public String getAllHouse(@PathVariable("agencyId") Long agencyId, String word, Model model) throws MyException {
        model.addAttribute("houses", houseService.getAllHouse(agencyId, word));
        model.addAttribute("word", word);
        model.addAttribute("ids", agencyId);
        System.out.println("agency id : " + agencyId);
        return "house/houseMainPage";
    }

    @GetMapping("/new")
    public String createHouse(@PathVariable Long agencyId, Model model) {
        model.addAttribute("agencyId", agencyId);
        model.addAttribute("house", new House());
        model.addAttribute("houseType",
                new String[]{"DETACHED_HOUSE",
                        "APARTMENT",
                        "LODGE",
                        "VILLA",
                        "HOUSEBOAT",
                        "CARAVAN"});
        return "house/newHouse";
    }

    @PostMapping("/newHouse")
    public String saveHouse(@PathVariable Long agencyId, @ModelAttribute("house") House house) {
        System.out.println("type : " + house.getHouseTypes());
        house.setHouseType(HouseType.valueOf(house.getHouseTypes()));
        houseService.saveHouse(agencyId, house);
        return "redirect:/houses/" + agencyId;
    }

    @GetMapping("/{houseId}/edit")
    public String edit(@PathVariable Long houseId, Model model, @PathVariable Long agencyId) throws MyException {
        model.addAttribute("house", houseService.getHouseById(houseId));
        model.addAttribute("houseTypes", new String[]{" DETACHED_HOUSE",
                "APARTMENT",
                "LODGE",
                "VILLA",
                "HOUSEBOAT",
                "CARAVAN"});
        model.addAttribute(agencyId);
        return "house/updateHouse";
    }

    @PostMapping("/{houseId}/update")
    public String saveUpdate(@ModelAttribute("house") House house, @PathVariable("houseId") Long id,
                             @PathVariable("agencyId") Long agencyId) {
        houseService.updateHouseById(id, house);
        return "redirect:/houses/" + agencyId;
    }

    @PostMapping("/{houseId}/delete")
    public String delete(@PathVariable Long houseId, @PathVariable("agencyId") Long agencyId) {
        houseService.deleteHouseById(houseId);
        return "redirect:/houses/" + agencyId;
    }
}
