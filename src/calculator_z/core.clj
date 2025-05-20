(ns calculator-z.core
  (:require [clj-http.client :as client])
  (:gen-class))

;; gravar as transações no atom
;; usar o swap!

(def api-url "https://caloriasporalimentoapi.herokuapp.com/api/calorias/")

 

(defn buscar-calorias [alimento]
  (let [res (client/get api-url
                        {:query-params {"descricao" alimento}
                        :as :json})]
    (:body res)))

(defn consulta-API []
   (let [entrada (read-line)
      resultado (buscar-calorias entrada)]
    (run!
      #(println (str "\"" (:descricao %) "\" contem " (:calorias %) " calorias por " (:quantidade %)))
      resultado)))


;; (defn ganho []
;;   (println "Chamando funcao ganho"))

(defn perda []
  (println "Chamando funcao perda"))


(defn -main []
  (println "\nMenu")
  (println "Opcao 1 - Consulta API")
  (println "Opcao 2 - Perda")
  (println "Opcao 3 - Sair")
  (def op (read))

  (cond
    (= op 1) (do (consulta-API) (recur))
    (= op 2) (do (perda) (recur))
    (= op 3) (println "Saindo...")))

(-main)