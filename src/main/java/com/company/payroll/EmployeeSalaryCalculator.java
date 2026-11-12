package com.company.payroll;

public class EmployeeSalaryCalculator {

    private static final double PF_RATE = 0.12; // 12% Provident Fund on Basic Pay

    /**
     * Calculates House Rent Allowance (HRA) based on Basic Pay and HRA percentage.
     */
    public double calculateHRA(double basicPay, double hraPercentage) {
        validateNonNegative(basicPay, "Basic pay");
        validatePercentage(hraPercentage, "HRA percentage");
        return basicPay * (hraPercentage / 100.0);
    }

    /**
     * Calculates Dearness Allowance (DA) based on Basic Pay and DA percentage.
     */
    public double calculateDA(double basicPay, double daPercentage) {
        validateNonNegative(basicPay, "Basic pay");
        validatePercentage(daPercentage, "DA percentage");
        return basicPay * (daPercentage / 100.0);
    }

    /**
     * Gross Salary = Basic Pay + HRA + DA
     */
    public double calculateGrossSalary(double basicPay, double hraPercentage, double daPercentage) {
        double hra = calculateHRA(basicPay, hraPercentage);
        double da = calculateDA(basicPay, daPercentage);
        return basicPay + hra + da;
    }

    /**
     * Total Deductions = 12% PF on Basic Pay + Tax Deduction + Other Deductions
     */
    public double calculateTotalDeductions(double basicPay, double taxDeduction, double otherDeductions) {
        validateNonNegative(basicPay, "Basic pay");
        validateNonNegative(taxDeduction, "Tax deduction");
        validateNonNegative(otherDeductions, "Other deductions");

        double pf = basicPay * PF_RATE;
        return pf + taxDeduction + otherDeductions;
    }

    /**
     * Net Salary = Gross Salary - Total Deductions
     */
    public double calculateNetSalary(double basicPay, double hraPercentage, double daPercentage,
                                   double taxDeduction, double otherDeductions) {
        double grossSalary = calculateGrossSalary(basicPay, hraPercentage, daPercentage);
        double deductions = calculateTotalDeductions(basicPay, taxDeduction, otherDeductions);

        if (deductions > grossSalary) {
            throw new IllegalArgumentException("Total deductions cannot exceed gross salary.");
        }
        return grossSalary - deductions;
    }

    private void validateNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
    }

    private void validatePercentage(double percentage, String fieldName) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException(fieldName + " must be between 0 and 100.");
        }
    }
}