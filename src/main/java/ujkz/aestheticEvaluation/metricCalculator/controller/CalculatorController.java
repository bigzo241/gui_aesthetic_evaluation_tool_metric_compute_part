package ujkz.aestheticEvaluation.metricCalculator.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ujkz.aestheticEvaluation.metricCalculator.metrics.*;
import ujkz.aestheticEvaluation.metricCalculator.util.JsonToObjects;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
public class CalculatorController {

    private final JSONObject jsonObject = new JSONObject();


    @GetMapping("/compute")
    public JSONObject compute(@RequestParam String jsonContent, @RequestParam double width_frame, @RequestParam double height_frame) {
        List<Composant> composantList;
        JsonToObjects toObjects = new JsonToObjects();
        Path json_path;

        try {
            json_path = Files.writeString(Paths.get("jsonFile1.json"), jsonContent, StandardCharsets.UTF_8);
            composantList = toObjects.toComposantList(Files.newInputStream(json_path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Balance balance = Balance.getInstance();
        Double BM = balance.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Balance : " + BM.floatValue());

        Equilibrium equilibrium = Equilibrium.getInstance();
        Double EM = equilibrium.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Equilibre : " + EM.floatValue());

        Symmetry symmetry = Symmetry.getInstance();
        Double SYM = symmetry.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Symmetry : " + SYM.floatValue());

        Sequence sequence = Sequence.getInstance();
        Double SQM = sequence.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Sequence : " + SQM.floatValue());

        Cohesion cohesion = Cohesion.getInstance();
        Double CM = cohesion.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Cohesion : " + CM.floatValue());

        Unity unity = Unity.getInstance();
        Double UM = unity.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Unity : " + UM.floatValue());

        Simplicity simplicity = Simplicity.getInstance();
        Double SM = simplicity.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Simplicity : " + SM.floatValue());

        Density density = Density.getInstance();
        Double DM = density.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Density : " + DM.floatValue());

        Economy economy  = Economy.getInstance();
        Double ECM = economy.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Economy : " + ECM.floatValue());

        Homogeneity homogeneity = Homogeneity.getInstance();
        Double HM = homogeneity.computeMetric(composantList, 798.0, 381.0);
        System.out.println("Homogeneity : " + HM.floatValue());

        jsonObject.put("Balance", BM);
        jsonObject.put("Equilibrium", EM);
        jsonObject.put("Symmetry", SYM);
        jsonObject.put("Sequence", SQM);
        jsonObject.put("Cohesion", CM);
        jsonObject.put("Unity", UM);
        jsonObject.put("Simplicity", SM);
        jsonObject.put("Density", DM);
        jsonObject.put("Economy", ECM);
        jsonObject.put("Homogeneity", HM);
        return jsonObject;
    }
}
