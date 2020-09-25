package clase;

import com.fuzzylite.Engine;
import com.fuzzylite.activation.General;
import com.fuzzylite.defuzzifier.Centroid;
import com.fuzzylite.norm.s.Maximum;
import com.fuzzylite.norm.t.Minimum;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

public class Stealth {
	public double difuso(int distancia, float camuflaje) {
		  	Engine engine = new Engine();
		    engine.setName("Stealth");
		    engine.setDescription("Sistema de deteccion de estado de un enemigo");

		    InputVariable Distancia = new InputVariable();
		    Distancia.setName("Distancia");
		    Distancia.setDescription("Distancia entre el enemigo y el jugador");
		    Distancia.setEnabled(true);
		    Distancia.setRange(0, 150);
		    Distancia.setLockValueInRange(false);

		    Trapezoid funcionDistanciaMuyCercana = new Trapezoid("MUY_CERCANA");
		    funcionDistanciaMuyCercana.setVertexC(0);
		    funcionDistanciaMuyCercana.setVertexD(50);
		    Distancia.addTerm(funcionDistanciaMuyCercana);

		    Distancia.addTerm(new Trapezoid("CERCANA", 45, 65, 85, 100));

		    Trapezoid funcionDistancialejana = new Trapezoid("LEJANA");
		    funcionDistancialejana.setVertexA(95);
		    funcionDistancialejana.setVertexB(150);
		    Distancia.addTerm(funcionDistancialejana);
		    engine.addInputVariable(Distancia);

		    InputVariable Camuflaje = new InputVariable();
		    Camuflaje.setName("Camuflaje");
		    Camuflaje.setDescription("Nivel de camuflaje del jugador");
		    Camuflaje.setEnabled(true);
		    Camuflaje.setRange(0, 100);
		    Camuflaje.setLockValueInRange(false);

		    Trapezoid funcionCamuflajeMuyBajo = new Trapezoid("MUY_BAJO");
		    funcionCamuflajeMuyBajo.setVertexC(0);
		    funcionCamuflajeMuyBajo.setVertexD(45);
		    Camuflaje.addTerm(funcionCamuflajeMuyBajo);

		    Camuflaje.addTerm(new Trapezoid("BAJO", 40, 55, 65, 80));

		    Trapezoid funcionCamuflajeAlto = new Trapezoid("ALTO");
		    funcionCamuflajeAlto.setVertexA(75);
		    funcionCamuflajeAlto.setVertexB(100);
		    Camuflaje.addTerm(funcionCamuflajeAlto);
		    engine.addInputVariable(Camuflaje);

		    OutputVariable Estado = new OutputVariable();
		    Estado.setName("Estado");
		    Estado.setDescription("Estado del enemigo");
		    Estado.setEnabled(true);
		    Estado.setRange(0, 100);
		    Estado.setLockValueInRange(false);
		    Estado.setAggregation(new Maximum());
		    Estado.setDefuzzifier(new Centroid(150));
		    Estado.setDefaultValue(Double.NaN);
		    Estado.setLockPreviousValue(false);

		    Trapezoid funcionEstadoPatrullaje = new Trapezoid("PATRULLAJE");
		    funcionEstadoPatrullaje.setVertexC(0);
		    funcionEstadoPatrullaje.setVertexD(40);
		    Estado.addTerm(funcionEstadoPatrullaje);

		    Estado.addTerm(new Trapezoid("ALERTA", 35, 45, 50, 60));
		    Estado.addTerm(new Trapezoid("ENCONTRADO", 55, 65, 75, 85));

		    Trapezoid funcionEstadoCombate = new Trapezoid("COMBATE");
		    funcionEstadoCombate.setVertexA(80);
		    funcionEstadoCombate.setVertexB(100);
		    Estado.addTerm(funcionEstadoCombate);
		    engine.addOutputVariable(Estado);

		    RuleBlock mamdani = new RuleBlock();
		    mamdani.setName("mamdani");
		    mamdani.setDescription("Min Max");
		    mamdani.setEnabled(true);
		    mamdani.setConjunction(new Minimum());
		    mamdani.setDisjunction(null);
		    mamdani.setImplication(new Minimum());
		    mamdani.setActivation(new General());
		    mamdani.addRule(Rule.parse("if Distancia is LEJANA and Camuflaje is ALTO then Estado is PATRULLAJE ", engine));
		    mamdani.addRule(Rule.parse("if Distancia is CERCANA and Camuflaje is ALTO then Estado is PATRULLAJE", engine));
		    mamdani.addRule(Rule.parse("if Distancia is MUY_CERCANA and Camuflaje is ALTO then Estado is ALERTA", engine));
		    mamdani.addRule(Rule.parse("if Distancia is LEJANA and Camuflaje is BAJO then Estado is PATRULLAJE", engine));
		    mamdani.addRule(Rule.parse("if Distancia is CERCANA and Camuflaje is BAJO then Estado is ALERTA", engine));
		    mamdani.addRule(Rule.parse("if Distancia is MUY_CERCANA and Camuflaje is BAJO then Estado is ENCONTRADO", engine));
		    mamdani.addRule(Rule.parse("if Distancia is LEJANA and Camuflaje is MUY_BAJO then Estado is ALERTA", engine));
		    mamdani.addRule(Rule.parse("if Distancia is CERCANA and Camuflaje is MUY_BAJO then Estado is ENCONTRADO", engine));
		    mamdani.addRule(Rule.parse("if Distancia is MUY_CERCANA and Camuflaje is MUY_BAJO then Estado is COMBATE", engine));
		    engine.addRuleBlock(mamdani);
			double d_distancia = (double) distancia;
			double d_camuflaje = (double) camuflaje;
			engine.setInputValue("Distancia", d_distancia);
		    engine.process();
		    engine.setInputValue("Camuflaje", d_camuflaje);
		    engine.process();
		    double estado = engine.getOutputValue("Estado");
		    return estado;
	  }
}
