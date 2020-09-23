package com.openclassrooms.testing.calcul.service;

import com.openclassrooms.testing.calcul.domain.model.CalculationModel;

public interface CalculatorService {
	/**
	 * Effectuer le calcul demandé par un modèle
	 * 
	 * @param
	 * @return Modèle de calcul rempli du résultat 
	 */
	public CalculationModel calculate(CalculationModel calculationModel);
}
