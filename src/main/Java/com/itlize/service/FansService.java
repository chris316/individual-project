package com.itlize.service;

import java.util.List;

import com.itlize.entity.Fans;
import com.itlize.entity.Project;
import com.itlize.exception.ResourceNotFoundException;

public interface FansService {
    public List<Fans> getFans();
    public Fans getFanById(int id);
    public Fans getFanByModel(String model);
    public List<Fans> getManufacturerFans(String manufacturer);
    public List<Fans> getFanSeries(String series);
    public List<Fans> getFanUseType(String useType);
    public List<Fans> getFanApplication(String application);
    public List<Fans> getFanByMountingLocation(String mountingLocation);
    public List<Fans> getFanByAccessories(String accessories);
    public List<Fans> getFanByModelYearMin(int modelYear);
    public List<Fans> getFanByModelYearMax(int modelYear);
    public List<Fans> getFanByModelYearParameters(int minYear, int maxYear);
    public List<Fans> getFanByAirflowMin(int airflowMin);
    public List<Fans> getFanByAirflowMax(int airflowMax);
    public List<Fans> getFanByAirflowParameters(int airflowMin, int airflowMax);
    public List<Fans> getFanByPowerMin(double powerMin);
    public List<Fans> getFanByPowerMax(double powerMax);
    public List<Fans> getFanByPower(double powerMin, double powerMax);
    public List<Fans> getFanByVoltageMin(int voltsMin);
    public List<Fans> getFanByVoltageMax(int voltsMax);
    public List<Fans> getFanByVoltage(int voltsMin, int voltsMax);
    public List<Fans> getFanBySpeedMin(int speedMin);
    public List<Fans> getFanBySpeedMax(int speedMax);
    public List<Fans> getFanBySpeed(int speedMin, int speedMax);
    public List<Fans> getFanByFanSpeeds(int fanSpeeds);
    public List<Fans> getFanBySound(int sound);
    public List<Fans> getFanBySoundParameters(int soundMin, int soundMax);
    public List<Fans> getFanByDiameter(int sweepDiameter);
    public List<Fans> getFanByDiameterParameters(int sweepMin, int sweepMax);
    public List<Fans> getFanByHeightMin(double heightMin);
    public List<Fans> getFanByHeightMax(double heightMax);
    public List<Fans> getFanByHeight(double heightMin, double heightMax);
    public List<Fans> getFanByWeightMin(double weightMin);
    public List<Fans> getFanByWeightMax(double maxWeight);
    public List<Fans> getFanByWeight(double minWeight, double maxWeight);
    public boolean addFan(Fans fan);
    public boolean deleteFan(int id);
    public boolean deleteFan(Fans fan);
    public void clear();
}
