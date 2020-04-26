package com.itlize.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itlize.entity.Fans;
import com.itlize.repository.FansRepository;
import com.itlize.service.FansService;

@Service
public class FansServiceImpl implements FansService{

    @Autowired
    private FansRepository fansRepository;

    @Override
    @Transactional
    public List<Fans> getFans(){return fansRepository.findAll();}

    @Override
    @Transactional
    public Fans getFanById(int id){
        return fansRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Fans getFanByModel(String model) {
        return fansRepository.findAllByModel(model);
    }

    @Override
    @Transactional
    public List<Fans> getManufacturerFans(String manufacturer) {
        return fansRepository.findAllByManufacturer(manufacturer);
    }

    @Override
    @Transactional
    public List<Fans> getFanSeries(String series) {
        return fansRepository.findAllBySeries(series);
    }

    @Override
    @Transactional
    public List<Fans> getFanUseType(String useType) {
        return fansRepository.findAllByUseType(useType);
    }

    @Override
    @Transactional
    public List<Fans> getFanApplication(String application) {
        return fansRepository.findAllByApplication(application);
    }

    @Override
    @Transactional
    public List<Fans> getFanByMountingLocation(String mountingLocation) {
        return fansRepository.findAllByMountingLocation(mountingLocation);
    }

    @Override
    @Transactional
    public List<Fans> getFanByAccessories(String accessories) {
        return fansRepository.findAllByAccessories(accessories);
    }

    @Override
    @Transactional
    public List<Fans> getFanByModelYearMin(int modelYear) {
        return fansRepository.findAllByModelYearIsGreaterThanEqual(modelYear);
    }

    @Override
    @Transactional
    public List<Fans> getFanByModelYearMax(int modelYear){
        return fansRepository.findAllByModelYearIsLessThanEqual(modelYear);
    }

    @Override
    @Transactional
    public List<Fans> getFanByModelYearParameters(int minYear, int maxYear) {
        return fansRepository.findAllByModelYearBetween(minYear, maxYear);
    }

    @Override
    @Transactional
    public List<Fans> getFanByAirflowMin(int airflowMin) {
        return fansRepository.findAllByAirflowIsGreaterThanEqual(airflowMin);
    }

    @Override
    @Transactional
    public List<Fans> getFanByAirflowMax(int airflowMax) {
        return fansRepository.findAllByAirflowIsLessThanEqual(airflowMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByAirflowParameters(int airflowMin, int airflowMax) {
        return fansRepository.findAllByAirflowBetween(airflowMin,airflowMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByPowerMin(double powerMin) {
        return fansRepository.findAllByPowerMinIsGreaterThanEqual(powerMin);
    }

    @Override
    @Transactional
    public List<Fans> getFanByPowerMax(double powerMax) {
        return fansRepository.findAllByPowerMaxIsLessThanEqual(powerMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByPower(double powerMin, double powerMax) {
        return fansRepository.findAllByPowerMinIsGreaterThanEqualAndPowerMaxIsLessThanEqual(powerMin,powerMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByVoltageMin(int voltsMin) {
        return fansRepository.findAllByOperatingVoltageMinIsGreaterThanEqual(voltsMin);
    }

    @Override
    @Transactional
    public List<Fans> getFanByVoltageMax(int voltsMax) {
        return fansRepository.findAllByOperatingVoltageMaxIsLessThanEqual(voltsMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByVoltage(int voltsMin, int voltsMax) {
        return fansRepository.findAllByOperatingVoltageMinIsGreaterThanEqualAndOperatingVoltageMaxIsLessThanEqual(voltsMin,voltsMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanBySpeedMin(int speedMin) {
        return fansRepository.findAllByFanSpeedMinIsGreaterThanEqual(speedMin);
    }

    @Override
    @Transactional
    public List<Fans> getFanBySpeedMax(int speedMax) {
        return fansRepository.findAllByFanSpeedMaxIsLessThanEqual(speedMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanBySpeed(int speedMin, int speedMax) {
        return fansRepository.findAllByFanSpeedMinIsGreaterThanEqualAndFanSpeedMaxIsLessThanEqual(speedMin,speedMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByFanSpeeds(int fanSpeeds) {
        return fansRepository.findAllByNumFanSpeeds(fanSpeeds);
    }

    @Override
    @Transactional
    public List<Fans> getFanBySound(int sound) {
        return fansRepository.findAllBySound(sound);
    }

    @Override
    @Transactional
    public List<Fans> getFanBySoundParameters(int soundMin, int soundMax) {
        return fansRepository.findAllBySoundBetween(soundMin,soundMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByDiameter(int sweepDiameter) {
        return fansRepository.findAllBySweepDiameter(sweepDiameter);
    }

    @Override
    @Transactional
    public List<Fans> getFanByDiameterParameters(int sweepMin, int sweepMax) {
        return fansRepository.findAllBySweepDiameterBetween(sweepMin,sweepMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByHeightMin(double heightMin) {
        return fansRepository.findAllByHeightMinIsGreaterThanEqual(heightMin);
    }

    @Override
    @Transactional
    public List<Fans> getFanByHeightMax(double heightMax) {
        return fansRepository.findAllByHeightMaxIsLessThanEqual(heightMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByHeight(double heightMin, double heightMax) {
        return fansRepository.findAllByHeightMinIsGreaterThanEqualAndHeightMaxIsLessThanEqual(heightMin,heightMax);
    }

    @Override
    @Transactional
    public List<Fans> getFanByWeightMin(double weightMin) {
        return fansRepository.findAllByWeightIsGreaterThanEqual(weightMin);
    }

    @Override
    @Transactional
    public List<Fans> getFanByWeightMax(double maxWeight) {
        return fansRepository.findAllByWeightIsLessThanEqual(maxWeight);
    }

    @Override
    @Transactional
    public List<Fans> getFanByWeight(double minWeight, double maxWeight) {
        return fansRepository.findAllByWeightBetween(minWeight,maxWeight);
    }

    @Override
    @Transactional
    public boolean addFan(Fans fan) {
            Optional<Fans> target = fansRepository.findById(fan.getId());
            if (target.isPresent()) {
                System.out.println("resource exist:" + fan.toString());
                return false;
            }
        try{
            fansRepository.save(fan);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        System.out.println("fan added.");
        return true;
    }

    @Override
    @Transactional
    public boolean deleteFan(int id) {
        try{
            fansRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteFan(Fans fan){
        try{
            fansRepository.delete(fan);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void clear(){fansRepository.deleteAll();}

}
