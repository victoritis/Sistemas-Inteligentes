clear all;
%load haberman.data;

entradas = xlsread('haberman.xlsx','entradas RNA')'; 
salidasDeseadas = xlsread('haberman.xlsx','salidas RNA')'; 

%rna = patternnet([8]);

arquitecturas = {[], [4], [7], [10], [12], [15], [20], [5 3], [8 3], [8 5], [10 5]};

for i=1:length(arquitecturas),
    arquitectura = arquitecturas{i};
    precisionEntrenamiento = [];
    precisionValidacion = []; 
    precisionTest = []; 

    for j=1:50,
        rna = patternnet(arquitectura);
        rna.trainParam.showWindow = false;
        [rna, tr] = train(rna, entradas, salidasDeseadas);
        salidas = sim(rna, entradas);
    
        precisionEntrenamiento(end+1) = 1-confusion(salidasDeseadas(:,tr.trainInd), salidas(:,tr.trainInd));
        precisionValidacion(end+1) = 1-confusion(salidasDeseadas(:,tr.valInd), salidas(:,tr.valInd));
        precisionTest(end+1) = 1-confusion(salidasDeseadas(:,tr.testInd), salidas(:,tr.testInd)); 
    end;

    mediaPrecisionTest = mean(precisionTest);
    desviacionTipicaPrecisionTest = std(precisionTest);
    
    fprintf ('Arquitectura [%s], Media: %f, Desviacion Tipica: %f\n',sprintf(' %d ', arquitectura),mediaPrecisionTest,desviacionTipicaPrecisionTest);

end;