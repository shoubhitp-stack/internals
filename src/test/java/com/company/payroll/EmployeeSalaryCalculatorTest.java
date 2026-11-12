package com.company.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeSalaryCalculatorTest {

    private EmployeeSalaryCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new EmployeeSalaryCalculator();
    }

    @Test
    @DisplayName("Verify HRA and DA calculations")
    void testAllowances() {
        assertEquals(10000.0, calculator.calculateHRA(50000.0, 20.0), 0.001);
        assertEquals(5000.0, calculator.calculateDA(50000.0, 10.0), 0.001);
    }

    @Test
    @DisplayName("Verify Gross Salary calculation (Basic + HRA + DA)")
    void testCalculateGrossSalary() {
        double gross = calculator.calculateGrossSalary(50000.0, 20.0, 10.0);
        // 50,000 + 10,000 (20% HRA) + 5,000 (10% DA) = 65,000
        assertEquals(65000.0, gross, 0.001);
    }

    @Test
    @DisplayName("Verify Total Deductions (12% PF + Tax + Other Deductions)")
    void testCalculateTotalDeductions() {
        double deductions = calculator.calculateTotalDeductions(50000.0, 2500.0, 500.0);
        // 6,000 (12% PF of 50,000) + 2,500 + 500 = 9,000
        assertEquals(9000.0, deductions, 0.001);
    }

    @Test
    @DisplayName("Verify Net Salary calculation (Gross - Deductions)")
    void testCalculateNetSalary() {
        double net = calculator.calculateNetSalary(50000.0, 20.0, 10.0, 2500.0, 500.0);
        // 65,000 Gross - 9,000 Deductions = 56,000
        assertEquals(56000.0, net, 0.001);
    }

    @Test
    @DisplayName("Verify exceptions for negative inputs and excessive deductions")
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateGrossSalary(-10000.0, 20.0, 10.0));

        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateHRA(50000.0, 120.0));

        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateNetSalary(20000.0, 10.0, 5.0, 25000.0, 0.0));
    }
}