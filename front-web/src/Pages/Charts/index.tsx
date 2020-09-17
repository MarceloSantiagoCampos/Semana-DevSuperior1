import React, { useEffect, useState } from 'react';
import Filters from '../../Components/Filters';
import './styles.css';
import Chart from 'react-apexcharts'; //instalar apexcharts via terminal
import { barOptions, pieOptions } from './chart-options';
import Axios from 'axios';
import { buildBarSeries, getGenderChartData, getPlatformChartData }from './helpers';

type PieChartData = {
    labels: string [],
    series: number []
}

type BarChartData = {
    x: string,
    y: number
}

const initialPieData = {
    labels: [],
    series: []
}

const BASE_URL = 'http://localhost:8080'

const Charts = () => {
    const[barChartData, setBarChartData] = useState<BarChartData[]>([]);
    const[platformData, setPlatformData] = useState<PieChartData[]>(initialPieData);
    const[genderData, setGenderData] = useState<PieChartData[]>(initialPieData);

    useEffect(() => {               //useEffect(() => {}, [vazio]), uma vez que esta vazio o ultimo []
        async function getData() {
            const recodsResponse = await Axios.get(`${BASE_URL}/records`)   //esse useEffect vai executar o que esta 
            const gamesResponse = await Axios.get(`${BASE_URL}/games`)      //dentro do {} assim que a aplicação carregar
            
            const barData= buildBarSeries(gamesResponse.data, recodsResponse.data.content); // recordsResponse.data verifica o conteudo dentro de rcordsResponse
            setBarChartData(barData);

            const platformChartData = getPlatformChartData(recodsResponse.data.content);   // recordsResponse.data.content acessa o conteudo dentro de content
            setPlatformData(platformChartData);                                            // que é o que realmente queremos [Ver Postman para entender melhor]

            const genderChartData = getGenderChartData(recodsResponse.data.content);
            setGenderData(genderChartData);
        }

        getData();
    }, [])

    return(
        <div className="page-container">
            <Filters link ="/records" linkText="VER TABELA" />
            <div className="chart-container">
                <div className="top-related">
                    <h1 className="top-related-title">
                        Jogos mais Votados
                    </h1>
                    <div className="games-container">
                        <Chart 
                            options={barOptions}
                            type="bar"
                            width="680"
                            height="650"
                            series={[{ data: barChartData}]}
                        />
                    </div>
                </div>
                <div className="charts">
                    <div className="platform-chart">
                        <h2 className="chart-title">Plataformas</h2>
                        <Chart 
                            options={{...pieOptions, labels:platformData?.labels}}
                            type="donut"
                            width="350"
                            series={platformData?.series}
                        />
                    </div>
                    <div className="gender-chart">
                        <h2 className="chart-title">Gêneros</h2>
                        <Chart 
                            options={{...pieOptions, labels:genderData?.labels }}
                            type="donut"
                            width="350"
                            series={genderData?.series}
                        />
                    </div>
                </div>
            </div>
        </div>
    );

}

export default Charts