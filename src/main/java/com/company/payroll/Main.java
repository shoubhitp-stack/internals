package com.company.payroll;

public class Main {
    public static void main(String[] args) {
        double basicPay = args.length > 0 ? Double.parseDouble(args[0]) : 50000.0;
        double hraPct = args.length > 1 ? Double.parseDouble(args[1]) : 20.0;
        double daPct = args.length > 2 ? Double.parseDouble(args[2]) : 10.0;
        double tax = args.length > 3 ? Double.parseDouble(args[3]) : 2500.0;
        double otherDeductions = args.length > 4 ? Double.parseDouble(args[4]) : 500.0;

        EmployeeSalaryCalculator calculator = new EmployeeSalaryCalculator();

        double hra = calculator.calculateHRA(basicPay, hraPct);
        double da = calculator.calculateDA(basicPay, daPct);
        double gross = calculator.calculateGrossSalary(basicPay, hraPct, daPct);
        double deductions = calculator.calculateTotalDeductions(basicPay, tax, otherDeductions);
        double net = calculator.calculateNetSalary(basicPay, hraPct, daPct, tax, otherDeductions);

        System.out.println("=== Employee Salary Breakdown ===");
        System.out.printf("Basic Pay        : %.2f%n", basicPay);
        System.out.printf("HRA (%.1f%%)      : %.2f%n", hraPct, hra);
        System.out.printf("DA (%.1f%%)       : %.2f%n", daPct, da);
        System.out.printf("Gross Salary     : %.2f%n", gross);
        System.out.printf("Total Deductions : %.2f%n", deductions);
        System.out.printf("Net Salary       : %.2f%n", net);
    }
}