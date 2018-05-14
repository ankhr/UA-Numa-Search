from gensim.models.doc2vec import Doc2Vec
import sys

model = Doc2Vec.load(sys.argv[1])
vec = model.infer_vector(sys.argv[0])

print(",".join([str(val) for val in vec]))
