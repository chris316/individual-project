package com.itlize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itlize.entity.Fans;

import java.util.List;

@Repository
public interface FansRepository extends JpaRepository<Fans,Integer> {
    public Fans findAllByModel(String model);
    public List<Fans> findAllByManufacturer(String manufacturer);
    public List<Fans> findAllBySeries(String series);
    public List<Fans> findAllByUseType(String useType);
    public List<Fans> findAllByApplication(String application);
    public List<Fans> findAllByMountingLocation(String application);
    public List<Fans> findAllByAccessories(String accessories);
    public List<Fans> findAllByModelYearIsGreaterThanEqual(int modelYear);
    public List<Fans> findAllByModelYearIsLessThanEqual(int modelyear);
    public List<Fans> findAllByModelYearBetween(int modelYearMin, int modelYearMax);
    public List<Fans> findAllByAirflowIsLessThanEqual(int airflowMax);
    public List<Fans> findAllByAirflowIsGreaterThanEqual(int airflowMin);
    public List<Fans> findAllByAirflowBetween(int airflowMin,int airflowMax);
    public List<Fans> findAllByPowerMax(double powerMax);
    public List<Fans> findAllByPowerMaxIsLessThanEqual(double powerMax);
    public List<Fans> findAllByPowerMaxBetween(double powerMaxMin,double powerMaxMax);
    public List<Fans> findAllByPowerMin(double powerMin);
    public List<Fans> findAllByPowerMinIsGreaterThanEqual(double powerMin);
    public List<Fans> findAllByPowerMinBetween(double powerMinMin, double powerMinMax);
    public List<Fans> findAllByPowerMinIsGreaterThanEqualAndPowerMaxIsLessThanEqual(double powerMin, double powerMax);
    public List<Fans> findAllByOperatingVoltageMax(int voltsMax);
    public List<Fans> findAllByOperatingVoltageMaxIsLessThanEqual(int voltsMax);
    public List<Fans> findAllByOperatingVoltageMin(int voltsMin);
    public List<Fans> findAllByOperatingVoltageMinIsGreaterThanEqual(int voltsMin);
    public List<Fans> findAllByOperatingVoltageMinIsGreaterThanEqualAndOperatingVoltageMaxIsLessThanEqual(int voltsMin, int voltsMax);
    public List<Fans> findAllByFanSpeedMax(int speedMax);
    public List<Fans> findAllByFanSpeedMaxIsLessThanEqual(int speedMax);
    public List<Fans> findAllByFanSpeedMin(int speedMin);
    public List<Fans> findAllByFanSpeedMinIsGreaterThanEqual(int speedMin);
    public List<Fans> findAllByFanSpeedMinIsGreaterThanEqualAndFanSpeedMaxIsLessThanEqual(int speedMin,int speedMax);
    public List<Fans> findAllByNumFanSpeeds(int speeds);
    public List<Fans> findAllBySound(int sound);
    public List<Fans> findAllBySoundBetween(int soundMin, int soundMax);
    public List<Fans> findAllBySweepDiameter(int diameter);
    public List<Fans> findAllBySweepDiameterBetween(int minDiameter, int maxDiameter);
    public List<Fans> findAllByHeightMax(double maxHeight);
    public List<Fans> findAllByHeightMaxIsLessThanEqual(double maxHeight);
    public List<Fans> findAllByHeightMin(double minHeight);
    public List<Fans> findAllByHeightMinIsGreaterThanEqual(double minHeight);
    public List<Fans> findAllByHeightMinIsGreaterThanEqualAndHeightMaxIsLessThanEqual(double minHeight, double maxHeight);
    public List<Fans> findAllByWeight(double weight);
    public List<Fans> findAllByWeightBetween(double minWeight, double maxWeight);
    public List<Fans> findAllByWeightIsLessThanEqual(double weight);
    public List<Fans> findAllByWeightIsGreaterThanEqual(double weight);




}
