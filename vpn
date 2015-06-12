/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querybasedcrawler;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Abhay
 */
public class Vpn {

    Scanner sc = new Scanner(System.in);
    LinkedList<String> vpnList; // This variable is used for saving new vpn list
    LinkedList<String> usedVpnList; //This variable is used for saving used vpn server
    int listUpdateCounter;
    //int vpnPass;

    // Vpn constructor for instansiation of usedList
    public Vpn() {
        vpnList = new LinkedList();
        usedVpnList = new LinkedList();
    }

    // This method is used for generating or Updating vpn list by taking from user in one by one or by csv
    public ArrayList<String> getVpnList() throws IOException {
        ArrayList<String> al = new ArrayList();
        if (this.listUpdateCounter > 0) {
            System.out.println("Your vpn list has less than minimum number of required vpn used by this program, Please add more vpn to list for resuming program");
            System.out.println("Please type 1 for adding vpn one by one OR type 2 by adding through csv file");
        } else {
            System.out.println("Hi first you have to add vpn for scrapping data , ip and port number in some format like this " + "37.187.61.127:8080");
            System.out.println("Please type 1 for adding vpn one by one OR type 2 by adding through csv file");
        }
        int resp = 4; // this value for entering in loop
        while (resp != 1 || resp != 2) { // this loop wiil be used when user give wrong input
            resp = sc.nextInt();
            if (resp == 1) { // When user will give input 1 then this part will work and ask 2 user to add manually one by one vpn
                al = this.getVpnOneByOne();
                System.out.println("Successfull added All vpn to our list");
                this.listUpdateCounter++;
                return al;

            } else if (resp == 2) {  // This section call when user want to add vpn by csv format, in this section we can extract all vpn add it to our list
                al = this.getVpnByCsv();
                System.out.println(al.size() + " Vpn Successfully added to our list");
                System.out.println(al.toString());
                this.listUpdateCounter++;
                return al;

            } else {
                System.out.println("Wrong input please try again");
                //resp = sc.nextInt();
            }
        }
        return al;
    }

    // This method is used for getting vpn list one by one from administrator
    public ArrayList<String> getVpnOneByOne() {
        ArrayList<String> al = new ArrayList();
        String resp2 = "y";//for entering in loop
        while (resp2.equalsIgnoreCase("Y")) {// for adding more element one by one
            System.out.print("Enter the vpn in given format ");
            String vpn = sc.next();
            while (!validateVpn(vpn)) {
                System.out.println("Enter the correct format of vpn, Try again");
                vpn = sc.next();
            }
            vpn = vpn.trim(); // for removing extra white space from ip
            al.add(vpn);
            System.out.println(vpn + " Successfully Added ");
            System.out.println("Type Y for enter next vpn or Type N for Next");
            resp2 = sc.next();
            if (resp2.equalsIgnoreCase("n")) {
                break;
            }
            while (!resp2.equalsIgnoreCase("Y")) {// this loop wiil be used when user give wrong input
                System.out.println("Enter the correct input , Y for yes and N for No");
                resp2 = sc.next();
            }
        }
        return al;
    }

    //This method is used for getting vpn list by CSV file
    public ArrayList<String> getVpnByCsv() throws FileNotFoundException, IOException {
        ArrayList<String> al = new ArrayList();
        System.out.println("Enter the full path of csv file of vpn list");
        String vpnCsv = sc.next();
        CSVReader br = new CSVReader(new FileReader(vpnCsv));
        String[] url;
        try {
            while ((url = br.readNext()) != null) {
                String temp = url[0].trim();
                if (this.validateVpn(temp)) {// This block is used for validating vpn from csv if not valid then not added in our list
                    al.add(temp);
                } else {
                    System.out.println("Wrong format of vpn removed successfully from list - " + temp);
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
        return al;
    }

    // This method is used for adding vpn list to our main list
    public void addVpn() throws IOException {
        this.vpnList = new LinkedList(this.getVpnList());
    }

    // This method is used for removing some bad vpn from our list
    public void removeVpn(String vpn) {
        if (vpnList.contains(vpn)) {
            vpnList.remove(vpn);
        } else {
            System.out.println(" Something Wrong ");
        }
    }

    // This method is used for updating vpn list
    public void updateVpn() throws IOException {
        this.vpnList.addAll(this.getVpnList());
    }

    // This method is used for creating DesireCapabilities object using vpn , it woul passed to webdriver
    public DesiredCapabilities vpn() throws IOException {
        if (this.vpnList.size() == 0) {
            this.updateVpn(); // For adding more vpn in our list
        }
        String PROXY = this.vpnList.getFirst();
        System.out.println("Vpn - " + PROXY);
        this.usedVpnList.add(PROXY);
        this.vpnList.removeFirst();
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setHttpProxy(PROXY)
                .setFtpProxy(PROXY)
                .setSslProxy(PROXY);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);

        return cap;
    }

    // for validation of vpn ip and port
    public boolean validateVpn(String vpn) {
        Pattern p = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})");
        Matcher m = p.matcher(vpn);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
