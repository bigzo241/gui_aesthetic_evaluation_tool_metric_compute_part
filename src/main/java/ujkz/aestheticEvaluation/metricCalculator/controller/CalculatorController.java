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
        Double EM = equilibrium.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Equilibre : " + EM.floatValue());

        Symmetry symmetry = Symmetry.getInstance();
        Double SYM = symmetry.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Symmetry : " + SYM.floatValue());

        Sequence sequence = Sequence.getInstance();
        Double SQM = sequence.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Sequence : " + SQM.floatValue());

        Cohesion cohesion = Cohesion.getInstance();
        Double CM = cohesion.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Cohesion : " + CM.floatValue());

        Unity unity = Unity.getInstance();
        Double UM = unity.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Unity : " + UM.floatValue());

        Simplicity simplicity = Simplicity.getInstance();
        Double SM = simplicity.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Simplicity : " + SM.floatValue());

        Density density = Density.getInstance();
        Double DM = density.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Density : " + DM.floatValue());

        Economy economy  = Economy.getInstance();
        Double ECM = economy.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Economy : " + ECM.floatValue());

        Homogeneity homogeneity = Homogeneity.getInstance();
        Double HM = homogeneity.computeMetric(composantList, width_frame, height_frame);
        System.out.println("Homogeneity : " + HM.floatValue());



        jsonObject.put("Balance", BM.floatValue());
        jsonObject.put("Equilibrium", EM.floatValue());
        jsonObject.put("Symmetry", SYM.floatValue());
        jsonObject.put("Sequence", SQM.floatValue());
        jsonObject.put("Cohesion", CM.floatValue());
        jsonObject.put("Unity", UM.floatValue());
        jsonObject.put("Simplicity", SM.floatValue());
        jsonObject.put("Density", DM.floatValue());
        jsonObject.put("Economy", ECM.floatValue());
        jsonObject.put("Homogeneity", HM.floatValue());
        return jsonObject;
    }
}
