/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Abhay
 */
//This class would responsible for adding , removing, updating vpn server list
public class Vpn {

    Scanner sc = new Scanner(System.in);
    LinkedList<String> vpnList; // This variable is used for saving new vpn list
    LinkedList<String> usedVpnList; //This variable is used for saving used vpn server
    final VpnValidator vpnValidator;
    int listUpdateCounter;

    // Vpn constructor for instansiation of usedList
    public Vpn() {
        vpnList = new LinkedList();
        usedVpnList = new LinkedList();
        vpnValidator = new VpnValidator();
    }

    //This method is used for getting vpn list by CSV file
    public ArrayList<String> getVpnList() throws FileNotFoundException, IOException {
        ArrayList<String> al = new ArrayList();
        if (this.listUpdateCounter > 0) {
            System.out.println("Vpn List is Epmty, Plz add some more vpn and press any number to resume program");
            String stemp = sc.next();
        }
        String vpnCsv = "Program-File/vpn.csv";
        CSVReader br = new CSVReader(new FileReader(vpnCsv));

        String[] url;
        try {

            while ((url = br.readNext()) != null) {
                String temp = url[0].trim();
                if (vpnValidator.isValid(temp)) {// This block is used for validating vpn from csv if not valid then not added in our list
                    al.add(temp);
                } else {
                    System.out.println("Wrong format of vpn, successfully removed from list - " + temp);
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

    // This method is used for updating vpn list
    public void updateVpn() throws IOException {
        this.vpnList.addAll(this.getVpnList());
    }

    // This method is used for removing some bad vpn from our list
    public void removeVpn(String vpn) {
        if (vpnList.contains(vpn)) {
            vpnList.remove(vpn);
        } else {
            System.out.println(" Something Wrong ");
        }
    }

    public String getVpn() throws IOException {// This method would return vpn from main list
        String tmp;
        if (this.vpnList.size() == 0) {
            this.addVpn();
        }
        tmp = this.vpnList.get(0);
        this.usedVpnList.add(tmp);
        this.vpnList.remove(tmp);

        return tmp;
    }

}
