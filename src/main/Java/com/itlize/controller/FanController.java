package com.itlize.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.itlize.entity.Fans;
import com.itlize.service.FansService;
import com.itlize.service.ProjectService;

@Controller
@RequestMapping("/fans")
public class FanController {

    private static final Logger LOG = LoggerFactory.getLogger(FanController.class);

    @Autowired
    private FansService fanService;
    @Autowired
    private ProjectService projectService;

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/list")
    public List<Fans> listFans(){
        return fanService.getFans();
    }

    @PostMapping("/addFan")
    public ResponseEntity<?> addFan(@RequestParam(name="manufacturer") String manufacturer, @RequestParam(name="series") String series, @RequestParam(name="model") String model, @RequestParam(name="useType") String useType, @RequestParam(name="application") String application, @RequestParam(name="mountLocation") String mountingLocation, @RequestParam(name="accessories") String accessories, @RequestParam(name="modelYear") Integer modelYear,
    @RequestParam(name="airflow") Integer airFlow, @RequestParam(name="powerMin") Double powerMin, @RequestParam(name="powerMax") Double powerMax, @RequestParam(name="voltsMin") Integer voltsMin, @RequestParam(name="voltsMax") Integer voltsMax, @RequestParam(name="speedMin") Integer fanSpeedMin, @RequestParam(name="speedMax") Integer fanSpeedMax, @RequestParam(name="speeds") Integer numSpeeds, @RequestParam(name="sound") Integer sound, @RequestParam(name="diameter") Integer diameter,
                          @RequestParam(name="heightMin") Double heightMin, @RequestParam(name="heightMax") Double heightMax, @RequestParam(name="weight") Double weight, @RequestParam(name="details") String details, @RequestParam(name="specSheet") String specSheet, @RequestParam(name="price") Double price, @RequestParam(name="image") String image){
        Fans fanToAdd=new Fans();
        fanToAdd.setAccessories(accessories);
        fanToAdd.setAirflow(airFlow);
        fanToAdd.setApplication(application);
        fanToAdd.setFanSpeedMax(fanSpeedMax);
        fanToAdd.setFanSpeedMin(fanSpeedMin);
        fanToAdd.setHeightMax(heightMax);
        fanToAdd.setHeightMin(heightMin);
        fanToAdd.setManufacturer(manufacturer);
        fanToAdd.setModel(model);
        fanToAdd.setModelYear(modelYear);
        fanToAdd.setMountingLocation(mountingLocation);
        fanToAdd.setNumFanSpeeds(numSpeeds);
        fanToAdd.setOperatingVoltageMax(voltsMax);
        fanToAdd.setOperatingVoltageMin(voltsMin);
        fanToAdd.setPowerMax(powerMax);
        fanToAdd.setPowerMin(powerMin);
        fanToAdd.setProdDetails(details);
        fanToAdd.setSeries(series);
        fanToAdd.setSound(sound);
        fanToAdd.setSpecSheet(specSheet);
        fanToAdd.setSweepDiameter(diameter);
        fanToAdd.setUseType(useType);
        fanToAdd.setWeight(weight);
        fanToAdd.setPrice(price);
        fanToAdd.setImageLink(image);
    fanService.addFan(fanToAdd);
    System.out.println("added fan");
        boolean isSuccessful = fanService.addFan(fanToAdd);
        return new ResponseEntity<>(String.format("{FanDetails\":%s}",fanToAdd.toString()), HttpStatus.OK);
    }

    @PostMapping("/deleteFan")
    public ResponseEntity<?> deleteFan(@RequestParam(name="fanId")Integer fanId){
        Fans fanToDelete=fanService.getFanById(fanId);
        if(fanToDelete==null){
            return new ResponseEntity<>("{\"error\":\"fan does not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        boolean isSuccessful = fanService.deleteFan(fanToDelete);
        return new ResponseEntity<>(String.format("{FanDetails\":%s}",fanToDelete.toString()), HttpStatus.OK);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findModels")
    public Fans fanModel(@RequestParam(name="model")String model){
        Fans fan=fanService.getFanByModel(model);
        return fan;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findManufacturers")
    public List<Fans>fanManufacturer(@RequestParam(name="manufacturer")String manufacturer){
        List<Fans> fans=fanService.getManufacturerFans(manufacturer);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findSeries")
    public List<Fans>fanSeries(@RequestParam(name="series")String series){
        List<Fans> fans=fanService.getFanSeries(series);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findUseType")
    public List<Fans> fanUseType(@RequestParam(name="useType")String useType){
        List<Fans> fans=fanService.getFanUseType(useType);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findApplications")
    public List<Fans>fanApplications(@RequestParam(name="applications")String applications){
        List<Fans> fans=fanService.getFanApplication(applications);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findMountingLocation")
    public List<Fans>fanLocations(@RequestParam(name="mountingLocation")String mountingLocation){
        List<Fans> fans=fanService.getFanByMountingLocation(mountingLocation);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findAccessories")
    public List<Fans>fanAccessories(@RequestParam(name="accessories")String accessories){
        List<Fans> fans=fanService.getFanByAccessories(accessories);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findYears")
    public List<Fans>fanYears(@RequestParam(name="yearMin", required=false)Integer yearMin, @RequestParam(name="yearMax",required=false)Integer yearMax){
        if(yearMin!=null&&yearMax==null)
        {
            List<Fans> fans=fanService.getFanByModelYearMin(yearMin);
            return fans;
        }
        else if(yearMin==null&&yearMax!=null)
        {
            List<Fans> fans=fanService.getFanByModelYearMax(yearMax);
            return fans;
        }
        else if(yearMin!=null&&yearMax!=null)
        {
            List<Fans>fans=fanService.getFanByModelYearParameters(yearMin,yearMax);
            return fans;
        }
        else if(yearMin==null&&yearMax==null)
        {
            List<Fans> fans=fanService.getFans();
            return fans;
        }
        List<Fans> fan=fanService.getFans();
        return fan;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findAirflow")
    public List<Fans>fanAirflow(@RequestParam(name="airflowMin", required=false)Integer airflowMin, @RequestParam(name="airflowMax",required=false)Integer airflowMax){
        if(airflowMin!=null&&airflowMax==null)
        {
            List<Fans> fans=fanService.getFanByAirflowMin(airflowMin);
            return fans;
        }
        else if(airflowMin==null&&airflowMax!=null)
        {
            List<Fans> fans=fanService.getFanByAirflowMax(airflowMax);
            return fans;
        }
        else if(airflowMin!=null&&airflowMax!=null)
        {
            List<Fans>fans=fanService.getFanByAirflowParameters(airflowMin,airflowMax);
            return fans;
        }
        else if(airflowMin==null&&airflowMax==null)
        {
            List<Fans> fans=fanService.getFans();
            return fans;
        }
        List<Fans> fans=fanService.getFans();
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findPower")
    public List<Fans>fanPower(@RequestParam(name="powerMin", required=false)Double powerMin, @RequestParam(name="powerMax",required=false)Double powerMax){
        if(powerMin!=null&&powerMax==null)
        {
            List<Fans> fans=fanService.getFanByPowerMin(powerMin);
            return fans;
        }
        else if(powerMin==null&&powerMax!=null)
        {
            List<Fans> fans=fanService.getFanByPowerMax(powerMax);
            return fans;
        }
        else if(powerMin!=null&&powerMax!=null)
        {
            List<Fans>fans=fanService.getFanByPower(powerMin,powerMax);
            return fans;
        }
        else if(powerMin==null&&powerMax==null)
        {
            List<Fans> fans=fanService.getFans();
            return fans;
        }
        List<Fans> fans=fanService.getFans();
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findVolts")
    public List<Fans>fanVolts(@RequestParam(name="voltsMin", required=false)Integer voltsMin, @RequestParam(name="voltsMax",required=false)Integer voltsMax){
        if(voltsMin!=null&&voltsMax==null)
        {
            List<Fans> fans=fanService.getFanByVoltageMin(voltsMin);
            return fans;
        }
        else if(voltsMin==null&&voltsMax!=null)
        {
            List<Fans> fans=fanService.getFanByVoltageMax(voltsMax);
            return fans;
        }
        else if(voltsMin!=null&&voltsMax!=null)
        {
            List<Fans>fans=fanService.getFanByVoltage(voltsMin,voltsMax);
            return fans;
        }
        else if(voltsMin==null&&voltsMax==null)
        {
            List<Fans> fans=fanService.getFans();
            return fans;
        }
        List<Fans> fans=fanService.getFans();
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findFanSpeed")
    public List<Fans>fanSpeed(@RequestParam(name="speedMin", required=false)Integer speedMin, @RequestParam(name="speedMax",required=false)Integer speedMax){
        if(speedMin!=null&&speedMax==null)
        {
            List<Fans> fans=fanService.getFanBySpeedMin(speedMin);
            return fans;
        }
        else if(speedMin==null&&speedMax!=null)
        {
            List<Fans> fans=fanService.getFanBySpeedMax(speedMax);
            return fans;
        }
        else if(speedMin!=null&&speedMax!=null)
        {
            List<Fans>fans=fanService.getFanBySpeed(speedMin,speedMax);
            return fans;
        }
        else if(speedMin==null&&speedMax==null)
        {
            List<Fans> fans=fanService.getFans();
            return fans;
        }
        List<Fans> fans=fanService.getFans();
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findSpeeds")
    public List<Fans>fanSpeeds(@RequestParam(name="speeds")Integer speeds){
        List<Fans> fans=fanService.getFanByFanSpeeds(speeds);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findSound")
    public List<Fans>fanSound(@RequestParam(name="soundMin")Integer soundMin, @RequestParam(name="soundMax")Integer soundMax){
        List<Fans> fans=fanService.getFanBySoundParameters(soundMin,soundMax);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findDiameter")
    public List<Fans>fanDiameter(@RequestParam(name="diameterMin")Integer diameterMin, @RequestParam(name="diameterMax")Integer diameterMax){
        List<Fans> fans=fanService.getFanByDiameterParameters(diameterMin,diameterMax);
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findHeight")
    public List<Fans>fanHeight(@RequestParam(name="heightMin", required=false)Double heightMin, @RequestParam(name="heightMax",required=false)Double heightMax){
        if(heightMin!=null&&heightMax==null)
        {
            List<Fans> fans=fanService.getFanByHeightMin(heightMin);
            return fans;
        }
        else if(heightMin==null&&heightMax!=null)
        {
            List<Fans> fans=fanService.getFanByHeightMax(heightMax);
            return fans;
        }
        else if(heightMin!=null&&heightMax!=null)
        {
            List<Fans>fans=fanService.getFanByHeight(heightMin,heightMax);
            return fans;
        }
        else if(heightMin==null&&heightMax==null)
        {
            List<Fans> fans=fanService.getFans();
            return fans;
        }
        List<Fans> fans=fanService.getFans();
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/findWeight")
    public List<Fans>fanWeight(@RequestParam(name="weightMin", required=false)Double weightMin, @RequestParam(name="weightMax",required=false)Double weightMax){
        if(weightMin!=null&&weightMax==null)
        {
            List<Fans> fans=fanService.getFanByWeightMin(weightMin);
            return fans;
        }
        else if(weightMin==null&&weightMax!=null)
        {
            List<Fans> fans=fanService.getFanByWeightMax(weightMax);
            return fans;
        }
        else if(weightMin!=null&&weightMax!=null)
        {
            List<Fans>fans=fanService.getFanByWeight(weightMin,weightMax);
            return fans;
        }
        else if(weightMin==null&&weightMax==null)
        {
            List<Fans> fans=fanService.getFans();
            return fans;
        }
        List<Fans> fans=fanService.getFans();
        return fans;
    }

}
