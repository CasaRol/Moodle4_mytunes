package com.experis.mytunes.dataAccess;

import com.experis.mytunes.models.BigSpender;
import com.experis.mytunes.models.Country;
import com.experis.mytunes.models.Customer;
import com.experis.mytunes.models.TopGenre;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDataHandler {

    private String URL = DataBaseConURL.URL;
    private Connection conn = DataBaseConURL.conn;



    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            //Query database
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country,PostalCode, Phone, Email FROM Customer");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public Customer addCustomer(Customer customer) {
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer(firstName, lastName, country, postalCode, phone, email) VALUES(?,?,?,?,?,?)");

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());

            int result = preparedStatement.executeUpdate();
            System.out.println(result);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return getOneCustomer(customer);
    }

    public Customer updateCustomer(Customer customer) {
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET FirstName=?, LastName=?, country=?, Postalcode=?, Phone=?, Email=? WHERE Customer.CustomerId=?");

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setInt(7, customer.getId());

            int result = preparedStatement.executeUpdate();
            System.out.println(result);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return getOneCustomer(customer);
    }

    public ArrayList<Country> getAllByCountry() {
        ArrayList<Country> countries = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            //Query database
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Customer.Country, COUNT(Country) FROM Customer GROUP BY Country ORDER BY COUNT(Country) DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                countries.add(
                        new Country(
                                resultSet.getString("Country"),
                                resultSet.getInt("COUNT(Country)")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return countries;
    }

    public ArrayList<BigSpender> getBigSpenders() {
        ArrayList<BigSpender> spenders = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            //Query database
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Customer.FirstName, Customer.LastName, SUM(Invoice.Total) FROM Invoice JOIN Customer ON Invoice.CustomerId=Customer.CustomerId  GROUP BY Invoice.CustomerId ORDER BY SUM(Total) DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                spenders.add(
                        new BigSpender(
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getDouble("SUM(Invoice.Total)")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return spenders;
    }

    public ArrayList<TopGenre> getCustomerGenre(int userId) {
        ArrayList<TopGenre> genres = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            //Query database
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Customer.FirstName, Customer.LastName, Genre.Name, COUNT(Genre.GenreId)\n" +
                            "FROM Customer\n" +
                            "JOIN Invoice ON Invoice.CustomerId=Customer.CustomerId\n" +
                            "JOIN InvoiceLine ON InvoiceLine.InvoiceId=Invoice.InvoiceId\n" +
                            "JOIN Track ON Track.TrackId=InvoiceLine.TrackId\n" +
                            "JOIN Genre ON Genre.GenreId=Track.GenreId\n" +
                            "WHERE Customer.CustomerId=?\n" +
                            "GROUP BY Genre.GenreId\n" +
                            "ORDER BY COUNT(Genre.GenreId) desc");

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                genres.add(
                        new TopGenre(
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Name"),
                                resultSet.getInt("COUNT(Genre.GenreId)")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return genres;
    }

    private Customer getOneCustomer(Customer customer) {
        Customer newCustomer = null;
        try {
            conn = DriverManager.getConnection(URL);

            //Query database
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country,PostalCode, Phone, Email FROM Customer WHERE Customer.Email=?");

            preparedStatement.setString(1, customer.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();


            newCustomer = new Customer(
                resultSet.getInt("CustomerId"),
                resultSet.getString("FirstName"),
                resultSet.getString("LastName"),
                resultSet.getString("Country"),
                resultSet.getString("PostalCode"),
                resultSet.getString("Phone"),
                resultSet.getString("Email")
            );


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return newCustomer;
    }
}
