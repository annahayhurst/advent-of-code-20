struct ExpensesCalculator {

    func findSumTo2020(in data: [Int]) -> Int {
        for index in 0...data.count {
            // Values before this in the array
            for lowerIndex in 0..<index {
                if data[lowerIndex] + data[index] == 2020 {
                    return data[lowerIndex] * data[index]
                }
            }

            // Values after this in the array
            for higherIndex in (index + 1)..<data.count {
                if data[higherIndex] + data[index] == 2020 {
                    return data[index] * data[higherIndex]
                }
            }
        }

        return -1
    }
    
}