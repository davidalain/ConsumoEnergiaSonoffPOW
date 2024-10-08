# eWeLink_SonoffPOWElite_CSV

Projeto Java simples para calcular o consumo de energia medido por um SONOFF POW ELITE entre um período de tempo com data e hora inicial e final.

O [SONOFF POW ELITE](https://sonoff.tech/product/diy-smart-switches/pow-elite/) consegue medir o consumo e armazenar os valores em kWh a cada hora. Um arquivo CSV é fornecido pelo App eWeLink contendo as medições dos últimos meses.

Modelos suportados:
- POWR316D
- POWR320D

Este projeto utiliza como entrada o arquivo CSV entregue pelo App eWeLink.

Exemplo de linhas do arquivo CSV:
```
data,time,consumption/KWh
2024/1/28,12:00-13:00,0.09
2024/1/28,13:00-14:00,0.09
2024/1/28,14:00-15:00,0.1
2024/1/28,15:00-16:00,0.1
2024/1/28,16:00-17:00,0.1
2024/1/28,17:00-18:00,0.07
2024/1/28,18:00-19:00,0.07
2024/1/28,19:00-20:00,0.07
2024/1/28,20:00-21:00,0.08
2024/1/28,21:00-22:00,0.08
2024/1/28,22:00-23:00,0.08
2024/1/28,23:00-24:00,0.08
2024/1/29,00:00-01:00,0
2024/1/29,01:00-02:00,0
2024/1/29,02:00-03:00,0
```
