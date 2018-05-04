package ru.stqa.pft.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import com.buzzbuzhome.GeoIPSoap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServideTests {

    @Test
    public void testMyIp() {
        new GeoIP().getGeoIPSoap();
       // GeoIPSoap geoIp = new GeoIPSoap().getUserLocation('2a02:2168:238d:5e00:fcb7:9508:3ce6:1e85');
    BBHLocation geoIp = new GeoIP().getGeoIPSoap12().getUserLocation("188.32.167.111");
    Assert.assertEquals(geoIp.getCountryCode(), "US");
    }
}
