# ConsumoEnergiaSonoffPOW

Projeto Java simples para calcular o consumo de energia medido por um SONOFF POW ELITE entre um período de tempo data e hora inicial e final.

O SONOFF POW ELITE consegue medir o consumo e armazenar os valores em kWh a cada hora. Um arquivo CSV é fornecido pelo App EweLink contendo as medições dos últimos meses.

Este projeto utiliza o como entrada arquivo CSV entregue pelo App Ewelink.

Exemplo de linhas do CSV:
```
data,time,consumption/KWh
2024/1/28,12:00-13:00,0
2024/1/28,13:00-14:00,0
2024/1/28,14:00-15:00,0
2024/1/28,15:00-16:00,0
2024/1/28,16:00-17:00,0
2024/1/28,17:00-18:00,0
2024/1/28,18:00-19:00,0
2024/1/28,19:00-20:00,0
2024/1/28,20:00-21:00,0
2024/1/28,21:00-22:00,0
2024/1/28,22:00-23:00,0
2024/1/28,23:00-24:00,0
2024/1/29,00:00-01:00,0
2024/1/29,01:00-02:00,0
2024/1/29,02:00-03:00,0
```
