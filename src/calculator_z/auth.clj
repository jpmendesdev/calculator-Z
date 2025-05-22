(ns calculator-z.auth
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))

(def api-key "WQ4WjQmEVSBzlWbpc3mvZnzAvTMhi2UEcAsoL56q")
(def base-url "https://api.nal.usda.gov/fdc/v1")

(defn calcular-calorias [nome quantidade]
  (let [search-url "https://api.nal.usda.gov/fdc/v1/foods/search"
        detail-url "https://api.nal.usda.gov/fdc/v1/food/"
        search-response (client/get search-url
                                    {:query-params {"query" nome
                                                    "api_key" api-key}
                                     :as :json})
        primeiro-alimento (first (get-in search-response [:body :foods]))
        fdc-id (:fdcId primeiro-alimento)
        detalhe-response (client/get (str detail-url fdc-id)
                                     {:query-params {"api_key" api-key}
                                      :as :json})
        dados (:body detalhe-response)
        porcao (:servingSize dados)
        unidade (:servingSizeUnit dados)
        nutrientes (:foodNutrients dados)
        calorias-por-porcao (some #(when (= (get-in % [:nutrient :number]) "208")
                                     (:amount %))
                                  nutrientes)]
    (if (and porcao calorias-por-porcao)
      (let [calorias (* (/ quantidade porcao) calorias-por-porcao)]
        {:descricao (:description primeiro-alimento)
         :porcao porcao
         :unidade unidade
         :calorias-por-porcao calorias-por-porcao
         :quantidade-consumida quantidade
         :calorias-consumidas (Math/round calorias)})
      {:erro "Informações nutricionais ou de porção não disponíveis."})))



